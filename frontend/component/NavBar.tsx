'use client'

import * as React from 'react';
import {Flex, Spacer, Text} from '@chakra-ui/react'
import Link from 'next/link';

type Props = {};
export const NavBar = (props: Props) => {

  return (
    <Flex
      h="10vh"
      alignItems="center"
      p="6"
      position="sticky"
      top="0"
      zIndex="sticky"
      w="full"
      bg={'blackAlpha.50'}
    >
      <Link style={{color: "inherit", display: "flex"}} href="/">
        <Text px="3" fontSize="xl" fontWeight="bold">
          Locations
        </Text>
      </Link>

      <Spacer/>

      <Link href="/add">
        <Text px="3" fontSize="sm" fontWeight="medium">
          Add
        </Text>
      </Link>
      <Link href="/list">
        <Text px="3" fontSize="sm" fontWeight="medium">
          List
        </Text>
      </Link>

      <Spacer/>

    </Flex>
  );
};
