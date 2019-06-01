/** * updated on 1/5/19. */package com.kv.retrorequestlib.helper;import java.io.File;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import okhttp3.MultipartBody;public abstract class DataModel {    private String baseUrl;    private String path1;    private String path2;    private String path3;    private String path4;    private String path5;    private String requestMethod = "GET";    private HashMap<String, String> header = new HashMap<>();    private HashMap<String, String> query = new HashMap<>();    private List<MultipartBody.Part> file;    private ResponseDelegate responseDelegate;    private int tag;    private boolean showToast = true;    private boolean showRetrySnack;    private static boolean showLog;    public DataModel(ResponseDelegate delegate) {        responseDelegate = delegate;    }    protected String getBaseUrl() {        return baseUrl;    }    public void setBaseUrl(String baseUrl) {        this.baseUrl = baseUrl;    }    protected String getPath1() {        return path1 != null ? path1 : "";    }    public void setPath1(String path1) {        this.path1 = path1;    }    protected String getPath2() {        return path2 != null ? path2 : "";    }    public void setPath2(String path2) {        this.path2 = path2;    }    protected String getPath3() {        return path3 != null ? path3 : "";    }    public void setPath3(String path3) {        this.path3 = path3;    }    protected String getPath4() {        return path4;    }    public void setPath4(String path4) {        this.path4 = path4;    }    protected String getPath5() {        return path5;    }    public void setPath5(String path5) {        this.path5 = path5;    }    protected String getRequestMethod() {        return requestMethod;    }    public void setRequestMethod(String requestMethod) {        this.requestMethod = requestMethod;    }    protected HashMap<String, String> getHeader() {        return header;    }    public void addHeader(String name, String value) {        if (header == null)            header = new HashMap<>();        header.put(name, value);    }    protected HashMap<String, String> getQuery() {        return query;    }    protected void setQuery(HashMap<String, String> query) {        this.query = query;    }    public void putQuery(String key, String value) {        if (query == null)            query = new HashMap<>();        query.put(key, value);    }    public void putQuery(String key, int value) {        if (query == null)            query = new HashMap<>();        query.put(key, String.valueOf(value));    }    protected List<MultipartBody.Part> getFile() {        return file;    }    public void putFile(String key, File value) {        if (file == null)            file = new ArrayList<>();        file.add(RequestBodyUtils.getRequestBodyImage(key, value));    }    protected void setFile(List<MultipartBody.Part> file) {        this.file = file;    }    protected ResponseDelegate getResponseDelegate() {        return responseDelegate;    }    protected int getTag() {        return tag;    }    public void setTag(int tag) {        this.tag = tag;    }    protected boolean isShowToast() {        return showToast;    }    public void setShowToast(boolean showToast) {        this.showToast = showToast;    }    protected boolean isShowRetrySnack() {        return showRetrySnack;    }    public void setShowRetrySnack(boolean showRetrySnack) {        this.showRetrySnack = showRetrySnack;    }    static boolean isShowLog() {        return showLog;    }    public void setShowLog(boolean showLog) {        this.showLog = showLog;    }}