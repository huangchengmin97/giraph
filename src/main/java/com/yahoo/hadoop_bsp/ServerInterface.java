package com.yahoo.hadoop_bsp;

import java.io.Closeable;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper.Context;

/**
 * interface for message communication server
 *
 * @param <I extends Writable> vertex id
 * @param <M extends Writable> message data
 *
 **/
@SuppressWarnings("rawtypes")
public interface ServerInterface<I extends WritableComparable,
                                 V extends Writable,
                                 E extends Writable,
                                 M extends Writable>
                                 extends Closeable,
                                 WorkerCommunications<I, V, E, M> {

    /**
     * Move the in transition messages to the in messages for every vertex and
     * add new connections to any newly appearing RPC proxies.
     */
    void prepareSuperstep();

    /**
     * Flush all outgoing messages.  This will synchronously ensure that all
     * messages have been send and delivered prior to returning.
     *
     * @throws IOException
     */
    void flush(Context context) throws IOException;

    /**
     * Closes all connections.
     *
     * @throws IOException
     */
    void closeConnections() throws IOException;

    /**
     * Shuts down.
     */
    void close();
}
