## Resource Design:
What resources should the consumer of this voting API call to vote?
Brainstorming a few approaches:
1) Have a 'voter' resource and a 'candidate' resource.
2) Have a 'vote' resource representing the voting processes.

A vote consists of 2 steps:
1) Updating a count for the voter to ensure they do not vote more than 3 times.
2) Updating a count for the candidate to contribute to the voting results.
 
I would not expect the consumer of this API to make 2 separate calls to make a vote. They do not need to know about the above steps involved in voting. Hence, I have gone with the approach of having a 'vote' resource which requires a voter id and candidate id to send a vote.

Reading on [coarse grained resources](https://www.thoughtworks.com/insights/blog/rest-api-design-resource-modeling).

## Table Design:
Voter (Voter ID, Vote Count)	- Vote count will be used to track the number of times the voter has voted.

Candidate (Candidate ID, Candidate Name, Votes) – Votes will be used to track the number of votes for a candidate.

## Potential pitfalls:
What happens if a Voter A has voted twice already and tries to vote via two different platforms simultaneously? If the simultaneous calls see that that the voter has not exceeded their number of votes then this would result in Voter A incorrectly making 4 votes in total.

To prevent this a check should be made at the point of updating the voter record to see if the voter is allowed to vote. Also, updating the voter record and candidate record needs to be completed as a transaction.

## Tools & Technologies Used:
Spring Tool Suite IDE, Java, Spring Boot, HSQL database

## How To Run:
1) Import project into STS.
2) Right click on project and Run As Java Application.
3) Once running you can use a REST client such as Advanced REST client for Google Chrome.

Rest calls you can make:
```
GET		http://localhost:8080/result
```
Shows the results of the voting.

```
GET		http://localhost:8080/voters
```
Shows the voters registered.

```
GET		http://localhost:8080/candidates
```
Shows the candidates registered.

```
POST	http://localhost:8080/vote
```
Submits a vote. You need to provide a voter id (representing the voter that is voting) and a candidate id (representing the candidate the vote is for). E.g.of content:
{
    "voterId":10,
    "candidateId":20
}
You can look up a voter/candidate id via calls to the voters/candidates resources. 
