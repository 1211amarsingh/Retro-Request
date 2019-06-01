/**
 * updated on 1/5/19.
 */
package com.kv.retrorequestlib.helper;

public interface ResponseDelegate {

    void onSuccess(int tag, String response);

    void onNoNetwork(int tag, String message);

    void onFailure(int tag, int response_code, String message);
}

