'use client'

import * as React from 'react';
import {ChakraProvider, Container} from "@chakra-ui/react";
import {NavBar} from "./NavBar";

type Props = {
  children: React.ReactNode;
};
export const ProviderWrapper = ({children}: Props) => {
  return (
    <ChakraProvider>
      <NavBar/>
      <Container maxW={"container.lg"}>
        {children}
      </Container>
    </ChakraProvider>
  );
};
