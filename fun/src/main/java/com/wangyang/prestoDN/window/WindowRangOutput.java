package com.wangyang.prestoDN.window;

import com.facebook.presto.common.block.BlockBuilder;
import com.facebook.presto.spi.function.RankingWindowFunction;

public class WindowRangOutput extends RankingWindowFunction {
    @Override
    public void reset() {
        super.reset();
    }

    public void processRow(BlockBuilder output, boolean newPeerGroup, int peerGroupCount, int currentPosition) {

    }
}
