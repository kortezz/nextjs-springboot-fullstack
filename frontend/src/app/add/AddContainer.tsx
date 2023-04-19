'use client'

import React, {FormEvent, useState} from 'react';
import {Input} from "@chakra-ui/input";
import {Button, Center, CreateToastFnReturn, FormControl, FormLabel, Toast, useToast} from "@chakra-ui/react";
import axios, {AxiosResponse, HttpStatusCode} from "axios";

type Props = {};

export default function AddContainer(props: Props): JSX.Element {
  const [loading, setLoading] = useState(false);
  const [city, setCity] = useState('');
  const [district, setDistrict] = useState('');

  const toast: CreateToastFnReturn = useToast();

  async function createLocation() {

    try {
      setLoading(true);
      const res : AxiosResponse = await axios.post("http://localhost:8080/api/locations", {
        city: city,
        district: district
      });
      if (res.status === HttpStatusCode.Ok) {
        setCity("");
        setDistrict("");
        setLoading(false);
        toast({
          title: 'Location created.',
          description: `City: ${city} District: ${district}`,
          status: 'success',
          duration: 3000,
          isClosable: true,
        })
      }
    } catch(exception) {
      setLoading(false);
      toast({
        title: 'Location can not be created.',
        description: `Location can not be created.`,
        status: 'error',
        duration: 3000,
        isClosable: true,
      })
    }
  }

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    await createLocation();
  }

  return (
    <form data-testid="add-container" onSubmit={handleSubmit}>
      <FormControl isInvalid={district === '' || city === ''}>
        <FormLabel>City</FormLabel>
        <Input isRequired placeholder="City" type='text' value={city} onChange={event => setCity(event.target.value)}/>
        <FormLabel paddingTop="10px">District</FormLabel>
        <Input isRequired placeholder="District" type='text' value={district}
               onChange={event => setDistrict(event.target.value)}/>
        <Center paddingTop="10px">
          <Button type="submit"
                  isLoading={loading}
                  loadingText='Submitting'
          >Submit</Button>
        </Center>
      </FormControl>
    </form>
  );
};
