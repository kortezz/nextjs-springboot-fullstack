import {Inter} from 'next/font/google'
import React from "react";
import {MainContainer} from "@/app/MainContainer";
import {NavBar} from "../../component/NavBar";

const inter = Inter({subsets: ['latin']})

export default function Home() {
  return (
    <>
      <MainContainer/>
    </>
  )
}
