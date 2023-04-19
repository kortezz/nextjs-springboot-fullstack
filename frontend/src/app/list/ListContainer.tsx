'use client'
import * as React from 'react';
import {Table, TableCaption, TableContainer, Tbody, Td, Tfoot, Th, Thead, Tr} from "@chakra-ui/table";
import {Location} from "../../../model/Location";
import {Card} from "@chakra-ui/react";

type Props = {
  locations: Location[]
};


export default function ListContainer({locations}: Props): JSX.Element {

  return (
    <Card data-testid="card">
    <TableContainer>
      <Table variant='simple'>
        <TableCaption>Locations</TableCaption>
        <Thead>
          <Tr>
            <Th>City</Th>
            <Th>District</Th>
          </Tr>
        </Thead>
        <Tbody>
          {locations.map(location =>
            <Tr key={location.id}>
              <Td>{location.city}</Td>
              <Td>{location.district}</Td>
            </Tr>
          )}
        </Tbody>
      </Table>
    </TableContainer>
    </Card>
  );
};
