import {render, screen} from "@testing-library/react";
import ListContainer from "@/app/list/ListContainer";
import {Location} from "../../../model/Location"

const locations: Location[] = [
  {
    "id": 1,
    "city": "city1",
    "district": "district1"
  }
]


describe('List Container', (): void => {
  it('Verify List Container', (): void => {
    render(<ListContainer locations={locations}/>);

    let headers: HTMLElement[] = screen.getAllByRole("columnheader");
    let cells: HTMLElement[] = screen.getAllByRole("cell");

    expect(screen.getByTestId("card")).toBeInTheDocument();
    expect(screen.getByRole('table')).toHaveTextContent("Locations");
    expect(headers[0]).toHaveTextContent("City");
    expect(headers[1]).toHaveTextContent("District");
    expect(cells[0]).toHaveTextContent("city1");
    expect(cells[1]).toHaveTextContent("district1");
  });
})
