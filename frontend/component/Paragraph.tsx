import {Divider, Heading, Text} from "@chakra-ui/react";
import * as React from "react";

type ParagraphProps = {
  heading: String;
  text: String;
};

export default function Paragraph({heading, text}: ParagraphProps) : JSX.Element {
  return <>
    <Heading size="md">
      {heading}
    </Heading>
    <Divider/>
    <Text>
      {text}
    </Text>
    <br/>
  </>;
}
