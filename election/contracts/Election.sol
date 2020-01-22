pragma solidity ^0.5.16;

/**
 * The Election contract does this and that...
 */
 contract Election {
  //Model a condidate
  struct Candidate {
  	uint id;
  	string name;
  	uint voteCount;
  }  
  //Store a condidate
  //Fetch a condidate
  mapping(uint => Candidate) public candidates;
  //Store a condidate
  uint public candidatesCount;
  constructor() public {
  	addCandidate("Narendra modi");
  	addCandidate("Rahul Gandhi");
  }

  //fuction for adding candidate in map list
  function addCandidate (string memory _name) private {
  	candidatesCount ++;
  	candidates[candidatesCount] = Candidate(candidatesCount, _name, 0);
  }
}

