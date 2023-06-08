package simblock.task;

import static simblock.simulator.Timer.getCurrentTime;

import java.math.BigInteger;

import simblock.block.ProofOfWorkBlock;
import simblock.node.Node;
import static simblock.simulator.Timer.removeTask;

public class SelfishMiningTask extends MiningTask {
    private final BigInteger difficulty;

    /**
     * Instantiates a new Mining task.
     *
     * @param minter     the minter
     * @param interval   the interval
     * @param difficulty the difficulty
     */
    // TODO how is the difficulty expressed and used here?
    public SelfishMiningTask(Node minter, long interval, BigInteger difficulty) {
        super(minter, interval, difficulty);
        this.difficulty = difficulty;
    }

    @Override
    public void run() {
        ProofOfWorkBlock createdBlock = new ProofOfWorkBlock(
                (ProofOfWorkBlock) this.getParent(), this.getMinter(), getCurrentTime(),
                this.difficulty);
        this.getMinter().addHiddenBlock(createdBlock);
        removeTask(this);
        this.getMinter().minting();
    }
}
