/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainexamples;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author karan.shah
 */
public class BlockChainExamples {
    
    static List<Block> blockChainList = new ArrayList<>();
    static int prefix = 3;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Block genesis = new Block("First block", "0");
        mineAndAddBlock(genesis);
        mineAndAddBlock(new Block("Hello", blockChainList.get(blockChainList.size() - 1).getHash()));
        mineAndAddBlock(new Block("World", blockChainList.get(blockChainList.size() - 1).getHash()));
        mineAndAddBlock(new Block("DZone", blockChainList.get(blockChainList.size() - 1).getHash()));
        System.out.println("---------------------");
        System.out.println("- BlockChain -");
        System.out.println("---------------------");
        blockChainList.forEach(System.out::println);
        System.out.println("---------------------");
        System.out.println("Is valid?: " + validate(blockChainList));
        System.out.println("---------------------");
        // corrupt block chain by modifying one of the block
        Block hiBlock = new Block("Hi", genesis.getHash());
        hiBlock.mineBlock(prefix);
        blockChainList.remove(2);
        blockChainList.add(2, hiBlock);
        System.out.println("Corrupted block chain by replacing 'Hello' block with 'Hi' Block");
        System.out.println("---------------------");
        System.out.println("- BlockChain -");
        System.out.println("---------------------");
        blockChainList.forEach(System.out::println);
        System.out.println("---------------------");
        System.out.println("Is valid?: " + validate(blockChainList));
        System.out.println("---------------------");
    }
    
    private static void mineAndAddBlock(Block newBlock){
        //if new block is successfully mined than add to to blockchain
        if (newBlock.mineBlock(prefix)) {
            blockChainList.add(newBlock);
        }
    }
    
    /**
     * @param blockChain  blockchain list.
     * @return true if blockchian is validate otherwise false
     */
    private static boolean validate(List<Block> blockChain) {
        boolean result = true;
        Block lastBlock = null;
        for (int i = blockChain.size() - 1; i >= 0; i--) {
            if (lastBlock == null) {
                lastBlock = blockChain.get(i);
            } else {
                Block current = blockChain.get(i);
                if (lastBlock.getPreviousHash() != current.getHash()) {
                    System.out.println("Corrupted strat from index : "+ i);
                    result = false;
                    break;
                }
                lastBlock = current;
            }
        }
        return result;
    }
}
