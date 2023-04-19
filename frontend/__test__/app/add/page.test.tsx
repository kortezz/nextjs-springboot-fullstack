import {render, screen} from "@testing-library/react";
import Add from "@/app/add/page";

describe('Add Page', () => {
  it('Verify Add Page', () => {
    render(<Add />);

    const page : HTMLElement  = screen.getByTestId('add-page');
    const container : HTMLElement  = screen.getByTestId('add-container');

    expect(page).toBeInTheDocument();
    expect(container).toBeInTheDocument();
  })
})
