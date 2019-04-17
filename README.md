# Travel Woes: Final Assignment

Alice and Bob love to travel and are looking at possible routes to get to their next destination. 
They have a few options in mind and as any other avid traveler, want to make the most out of the trip whilst spending as little as possible. 
Their first step is obviously to reduce the cost of travel. </br>
You are their travel agent and your task is to find out the cheapest possible route they should take for their next travel, by computing the minimum cost to travel to the destination they specify.
You are give N cities including the city Alice and Bob reside in. There are M flight routes connecting the cities. Each route would get you from one end to the other, but not the other way around,
and you are provided with the cost to take the flight operating in that route.
Given the number of cities and details of the flight routes, compute the minimum cost to travel from the origin (say S) to the destination say (D). If there is no route from S to D, the cost should
be -1;

## Input Format:
One line containing N and M, followed by M lines containing a list of M space-seperated triplets (U,V,X) meaning there is a flight from U to V costing X dollars, followed by a line containiing the origin (S) and the destination (D).

### Example:

```
4 4
1 2 1
4 1 2
2 3 2
1 3 5
1 3
```

## Output:
Output the minimum cost to travel from S to D, or -1 if there is no path

### Output of given Example:
```
3
```

### Notes
1. Your solution should be effecient (it should run in O((N+M)logN)).
2. Do not import, include, or use any additional libraries unless dealing with System inputs. (For Java, you can't use anything from java.util.\* other than the Scanner)
