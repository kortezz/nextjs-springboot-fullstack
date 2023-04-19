import * as React from 'react';
import ListContainer from "@/app/list/ListContainer";
import {Location} from "../../../model/Location";
import axios, {AxiosResponse} from "axios";

type Props = {
  data: Location[]
};

async function getLocations() : Promise<any> {
  const res : AxiosResponse = await axios.get("http://localhost:8080/api/locations");
  return res.data;
}

async function List(): Promise<JSX.Element> {
  const data: Location[] = await getLocations();

  return (
    <ListContainer locations={data}/>
  );
};

export default List;
