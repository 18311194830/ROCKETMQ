package batch;

import org.apache.rocketmq.common.message.Message;

import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: ListSplitter
 * @Description: 消息切割
 * @Author: 任宏腾
 * @CreateDate: 2020/9/13$ 9:22$
 * @Version: 1.0
 */

public class ListSplitter implements Iterable<List<Message>> {
    private final int LIMIT_SIZE = 1024*1024*4;
    private final List<Message> messageList;

    private int currIndex;

    public ListSplitter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public Iterator<List<Message>> iterator() {
        return null;
    }
}
