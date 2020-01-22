pragma solidity ^0.5.16;

/**
 * The Election contract does this and that...
 */
 contract Election {
  //Model a condidate
  struct Candidate {
  	uint id;
  	string name;
  	string party;
  	uint voteCount;
  }  
  //Store a condidate
  //Fetch a condidate
  mapping(uint => Candidate) public candidates;
  //Store a condidate
  uint public candidatesCount;
  constructor() public {
  	addCandidate("Narendra modi", "BJP");
  	addCandidate("Rahul Gandhi", "Congress");
  	addCandidate("Arvind kejriwal", "AAP");
  }

  //fuction for adding candidate in map list
  function addCandidate (string memory _name, string memory _party) private {
  	candidatesCount ++;
  	candidates[candidatesCount] = Candidate(candidatesCount, _name, _party, 0);
  }
}

