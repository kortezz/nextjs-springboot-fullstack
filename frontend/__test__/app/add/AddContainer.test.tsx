import {fireEvent, render, screen, waitFor, waitForElementToBeRemoved} from '@testing-library/react'
import '@testing-library/jest-dom'
import AddContainer from "@/app/add/AddContainer";
import {HttpStatusCode} from "axios";
import {Location} from "../../../model/Location";

const axios = require('axios');

jest.mock("axios");

describe('Add Container', (): void => {

  beforeEach(() => {
    render(<AddContainer/>);
  });

  it('Verify Add Container', (): void => {

    expect(screen.getByTestId('add-container')).toBeInTheDocument();
    expect(screen.getByText("City")).toBeInTheDocument();
    expect(screen.getByText("District")).toBeInTheDocument();

    let allByRole: HTMLElement[] = screen.getAllByRole('textbox');
    expect(allByRole.length).toBe(2);

    expect(screen.getByRole('button')).toBeInTheDocument();
  })

  it('Post api should call after button clicked', async (): Promise<void> => {

    let allByRole: HTMLElement[] = screen.getAllByRole('textbox');
    fireEvent.change(allByRole[0], {target: {value: 'city1'}});
    fireEvent.change(allByRole[1], {target: {value: 'district1'}});

    let data: Location = {"city": "city1", "district": "district1"};
    axios.post.mockResolvedValueOnce({data: data, status: HttpStatusCode.Ok});

    fireEvent.click(screen.getByRole('button'))

    await waitFor(() => {
      expect(screen.getByText('Loading...')).toBeInTheDocument();
      expect(axios.post).toBeCalledTimes(1);
      expect(axios.post).toBeCalledWith('http://localhost:8080/api/locations', data);
    })
  })

  it('Textboxes should be clear after api called', async (): Promise<void> => {

    let allByRole: HTMLElement[] = screen.getAllByRole('textbox');
    fireEvent.change(allByRole[0], {target: {value: 'city1'}});
    fireEvent.change(allByRole[1], {target: {value: 'district1'}});

    let data: Location = {"city": "city1", "district": "district1"};
    axios.post.mockResolvedValueOnce({data: data, status: HttpStatusCode.Ok});

    fireEvent.click(screen.getByRole('button'))

    await waitForElementToBeRemoved(() => screen.getByText('Loading...'));

    screen.getAllByRole('textbox').map((textbox: HTMLElement): void => {
      expect(textbox).toHaveValue('');
    })
  })

  it('should catch exception after api response is not OK', async () => {

    let allByRole: HTMLElement[] = screen.getAllByRole('textbox');
    fireEvent.change(allByRole[0], {target: {value: 'city1'}});
    fireEvent.change(allByRole[1], {target: {value: 'district1'}});

    axios.post.mockImplementation(() => {
      throw new Error();
    });

    fireEvent.click(screen.getByRole('button'))

    const textbox: HTMLElement[] = screen.getAllByRole('textbox');
    expect(textbox[0]).toHaveValue('city1');
    expect(textbox[1]).toHaveValue('district1');
  })
})
