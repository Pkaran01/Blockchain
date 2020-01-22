var Election = artifacts.require("./Election.sol");

contract("Election", function(accounts){
	var electionInstance;

	it("initializes with two candidates", function() {
		return Election.deployed().then(function(instance) {
			return instance.candidatesCount();
		}).then(function(count) {
			assert.equal(count, 3);
		});
	});

	it("it initializes the candidates with the correct values", function() {
		return Election.deployed().then(function(instance) {
			electionInstance = instance;
			return electionInstance.candidates(1);
		}).then(function(candidate) {
			assert.equal(candidate[0], 1, "contains the correct id");
			assert.equal(candidate[1], "Narendra modi", "contains the correct name");
			assert.equal(candidate[2], "BJP", "contains the correct name");
			assert.equal(candidate[3], 0, "contains the correct votes count");
			return electionInstance.candidates(2);
		}).then(function(candidate) {
			assert.equal(candidate[0], 2, "contains the correct id");
			assert.equal(candidate[1], "Rahul Gandhi", "contains the correct name");
			assert.equal(candidate[2], "Congress", "contains the correct name");
			assert.equal(candidate[3], 0, "contains the correct votes count");
			return electionInstance.candidates(3);
		}).then(function(candidate) {
			assert.equal(candidate[0], 3, "contains the correct id");
			assert.equal(candidate[1], "Arvind kejriwal", "contains the correct name");
			assert.equal(candidate[2], "AAP", "contains the correct name");
			assert.equal(candidate[3], 0, "contains the correct votes count");
		});
	});

});