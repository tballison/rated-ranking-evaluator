package io.sease.rre.search.api;

import java.io.Closeable;
import java.io.File;
import java.util.Map;

/**
 * A supertype layer interface for denoting the behaviour expected by a given search platform.
 * A behaviour in this perspective means all lifecycle API methods needed for controlling and interacting with
 * a given search engine instance.
 *
 * @author agazzarini
 * @since 1.0
 */
public interface SearchPlatform extends Closeable {
    /**
     * Starts this search platform.
     *
     * @param configuration the platform configuration.
     */
    void beforeStart(Map<String, Object> configuration);

    /**
     * Loads some data in a given index.
     *
     * @param corpus          the data.
     * @param configFolder    the folder that contains the configuration for the given index.
     * @param targetIndexName the name of the index where data will be indexed.
     */
    void load(final File corpus, final File configFolder, final String targetIndexName);

    /**
     * Starts this search platform.
     */
    void start();

    /**
     * Initialises this search platform.
     */
    void afterStart();

    /**
     * Releases any resource.
     */
    void beforeStop();

    /**
     * Executes the given query.
     * The semantic of the input query may change between concrete platforms
     *
     * @param indexName the index name that holds the data.
     * @param query     the query.
     * @param maxRows   the maximum number of rows that will be returned.
     * @return the response of the query execution.
     */
    QueryOrSearchResponse executeQuery(String indexName, String query, final String[] fields, int maxRows);

    /**
     * Returns the name of this search platform.
     *
     * @return the name of this search platform.
     */
    String getName();

    /**
     * Does the platform require a refresh, outside of any concerns about
     * corpora updates, etc. This is likely to be true when the data directory
     * has been deleted, for example.
     *
     * @return {@code true} if the platform needs to be refreshed.
     */
    boolean isRefreshRequired();

    /**
     * Is the given file a search platform configuration container?
     * Ie. can it be used to initialise and/or configure a search platform
     * index?
     *
     * @param indexName the index name being processed.
     * @param file the file to be tested.
     * @return {@code true} if the file is a search platform config file or
     * directory.
     */
    boolean isSearchPlatformFile(String indexName, File file);

    /**
     * @return {@code true} if this platform requires a corpora file to be
     * loaded in order to run.
     */
    boolean isCorporaRequired();
}
