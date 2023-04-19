'use client'
// @flow
import * as React from 'react';
import {Card, Container, Divider, Heading, Text} from "@chakra-ui/react";
import Paragraph from "../../component/Paragraph";

type Props = {};

function ParagraphX() {
  return <>
    <Heading>
      Lake Eğirdir
    </Heading>
    <Divider/>
    <Text>
      Eğirdir (Turkish: Eğirdir Gölü, formerly Eğridir) is a lake in the Lakes Region of Turkey. The town of Eğirdir
      lies near its southern end, 107 kilometers (67 mi) north of Antalya. With an area of 482 square kilometres (186
      sq mi) it is the fourth largest lake in Turkey, and the second largest freshwater lake.
    </Text>
    <br/>
  </>;
}

export const MainContainer = (props: Props) => {
  return (
    <Card>
      <Paragraph heading="Lake Eğirdir" text="Eğirdir (Turkish: Eğirdir Gölü, formerly Eğridir) is a lake in the Lakes Region of Turkey. The town of Eğirdir
      lies near its southern end, 107 kilometers (67 mi) north of Antalya. With an area of 482 square kilometres (186
      sq mi) it is the fourth largest lake in Turkey, and the second largest freshwater lake."/>
      <Paragraph heading="Name" text="The town and the lake were formerly called Eğridir, a Turkish pronunciation of the town's old Greek name
        Akrotiri. Eğridir means it is crooked in Turkish, so to remove the negative connotations, in the mid-1980s the
        i and the r were transposed in a new official name, thus creating Eğirdir, a name that evokes spinning and
        flowers,[1] although many people in Turkey still call both the town and the lake by its former name."/>
      <Paragraph heading="Hydrology" text="Lake Eğirdir is fed by about 40 different springs, some of which are intermittent,
      and also by rainfall within its 3,309-km² drainage basin.[2]: 797  The main streams which feed Lake Eğirdir are the Pupa, the Hoyran,
      the Yalvaç, and the Çay.[3]: 702  Besides evaporation, water exits Lake Eğirdir either by flowing out through the Kovada Canal into Lake Kovada,
      by draining out into one of the about 20 natural ponors that exist at the bottom of the lake, or by being pumped out through one of the
      11 irrigation pumps built around the lake.[2]: 797  The average retention time for water in the lake is 2.5 to 3 years.[2]: 796 
      The lake has an average depth of 7 m and a maximum depth of 13 m.[2]: 796  It has a total volume of 4,000 hm³, of which 1,000 hm³ is
      drawn off for irrigation, drinking water, or other human uses.[2]: 796  Approximately 45,000 hectares are irrigated by waters drawn
      from the lake.[2]: 797  There is no thermal stratification in the lake.[2]: 797 " />
      <Paragraph heading="Island" text="Lake Eğirdir has two islands, connected to the mainland by a long causeway into the town of Eğirdir:
      Can Ada (meaning Life Island) the smaller of the two islands.
      Yeşil Ada (Green Island, formerly known as Nis) - until 1923, was home to a Greek community living in stone and timber houses." />
      <Paragraph heading="Wildlife" text="Beginning with Karekin Deveciyan's Türkiye'de Balık ve Balıkçılık in 1915, a total of 15 different
      fish species have been recorded in Lake Eğirdir.[4]: 277–8, 283–4  Of these, 7 are endemic species that still inhabit the lake,
      2 are endemic species that are now locally extinct, 4 are introduced species, 1 is of uncertain origin but is native to the area,
      and 1 is of unknown status but likely an exotic species.[4]: 277–8  Introduction of invasive species since the 1950s, along with overfishing,
      has caused significant disruption in the local ecosystem.[4]: 277–8  The first major change came in 1955, when the non-native pike perch,
      which preys on other fish, was intentionally introduced to the lake.[4]: 278  The reason was that the lake's native fish were not
      very economically valuable for commercial fishing.[2]: 797  The population dynamics of the lake's ecosystem
      rapidly collapsed, and two endemic species became locally extinct.[4]: 278  Since then, other exotic species have been introduced to the lake,
      such as the omnivorous Prussian carp by 1996 and the plankton- and fish-eating big-scale sand smelt by 2003.[4]: 278 " />
    </Card>
  );
};
