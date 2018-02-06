// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proxyentry.proto

package com.bfm.mf.crawler.proxyserver;

/**
 * Protobuf type {@code proxy_server.CrawlResponse}
 *
 * <pre>
 *返回参数
 * </pre>
 */
public  final class CrawlResponse extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:proxy_server.CrawlResponse)
    CrawlResponseOrBuilder {
  // Use CrawlResponse.newBuilder() to construct.
  private CrawlResponse(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private CrawlResponse() {
    html_ = "";
    charset_ = "";
    httpCode_ = 0;
    url_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private CrawlResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry) {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            html_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            charset_ = s;
            break;
          }
          case 29: {

            httpCode_ = input.readSFixed32();
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            url_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw new RuntimeException(e.setUnfinishedMessage(this));
    } catch (java.io.IOException e) {
      throw new RuntimeException(
          new com.google.protobuf.InvalidProtocolBufferException(
              e.getMessage()).setUnfinishedMessage(this));
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_CrawlResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_CrawlResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.bfm.mf.crawler.proxyserver.CrawlResponse.class, com.bfm.mf.crawler.proxyserver.CrawlResponse.Builder.class);
  }

  public static final int HTML_FIELD_NUMBER = 1;
  private volatile java.lang.Object html_;
  /**
   * <code>optional string html = 1;</code>
   */
  public java.lang.String getHtml() {
    java.lang.Object ref = html_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      html_ = s;
      return s;
    }
  }
  /**
   * <code>optional string html = 1;</code>
   */
  public com.google.protobuf.ByteString
      getHtmlBytes() {
    java.lang.Object ref = html_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      html_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CHARSET_FIELD_NUMBER = 2;
  private volatile java.lang.Object charset_;
  /**
   * <code>optional string charset = 2;</code>
   */
  public java.lang.String getCharset() {
    java.lang.Object ref = charset_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      charset_ = s;
      return s;
    }
  }
  /**
   * <code>optional string charset = 2;</code>
   */
  public com.google.protobuf.ByteString
      getCharsetBytes() {
    java.lang.Object ref = charset_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      charset_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int HTTPCODE_FIELD_NUMBER = 3;
  private int httpCode_;
  /**
   * <code>optional sfixed32 httpCode = 3;</code>
   */
  public int getHttpCode() {
    return httpCode_;
  }

  public static final int URL_FIELD_NUMBER = 4;
  private volatile java.lang.Object url_;
  /**
   * <code>optional string url = 4;</code>
   */
  public java.lang.String getUrl() {
    java.lang.Object ref = url_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      url_ = s;
      return s;
    }
  }
  /**
   * <code>optional string url = 4;</code>
   */
  public com.google.protobuf.ByteString
      getUrlBytes() {
    java.lang.Object ref = url_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      url_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getHtmlBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, html_);
    }
    if (!getCharsetBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessage.writeString(output, 2, charset_);
    }
    if (httpCode_ != 0) {
      output.writeSFixed32(3, httpCode_);
    }
    if (!getUrlBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessage.writeString(output, 4, url_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getHtmlBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, html_);
    }
    if (!getCharsetBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, charset_);
    }
    if (httpCode_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeSFixed32Size(3, httpCode_);
    }
    if (!getUrlBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(4, url_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.bfm.mf.crawler.proxyserver.CrawlResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.bfm.mf.crawler.proxyserver.CrawlResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code proxy_server.CrawlResponse}
   *
   * <pre>
   *返回参数
   * </pre>
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:proxy_server.CrawlResponse)
      com.bfm.mf.crawler.proxyserver.CrawlResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_CrawlResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_CrawlResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.bfm.mf.crawler.proxyserver.CrawlResponse.class, com.bfm.mf.crawler.proxyserver.CrawlResponse.Builder.class);
    }

    // Construct using com.bfm.mf.crawler.proxyserver.CrawlResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      html_ = "";

      charset_ = "";

      httpCode_ = 0;

      url_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_CrawlResponse_descriptor;
    }

    public com.bfm.mf.crawler.proxyserver.CrawlResponse getDefaultInstanceForType() {
      return com.bfm.mf.crawler.proxyserver.CrawlResponse.getDefaultInstance();
    }

    public com.bfm.mf.crawler.proxyserver.CrawlResponse build() {
      com.bfm.mf.crawler.proxyserver.CrawlResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.bfm.mf.crawler.proxyserver.CrawlResponse buildPartial() {
      com.bfm.mf.crawler.proxyserver.CrawlResponse result = new com.bfm.mf.crawler.proxyserver.CrawlResponse(this);
      result.html_ = html_;
      result.charset_ = charset_;
      result.httpCode_ = httpCode_;
      result.url_ = url_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.bfm.mf.crawler.proxyserver.CrawlResponse) {
        return mergeFrom((com.bfm.mf.crawler.proxyserver.CrawlResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.bfm.mf.crawler.proxyserver.CrawlResponse other) {
      if (other == com.bfm.mf.crawler.proxyserver.CrawlResponse.getDefaultInstance()) return this;
      if (!other.getHtml().isEmpty()) {
        html_ = other.html_;
        onChanged();
      }
      if (!other.getCharset().isEmpty()) {
        charset_ = other.charset_;
        onChanged();
      }
      if (other.getHttpCode() != 0) {
        setHttpCode(other.getHttpCode());
      }
      if (!other.getUrl().isEmpty()) {
        url_ = other.url_;
        onChanged();
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.bfm.mf.crawler.proxyserver.CrawlResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.bfm.mf.crawler.proxyserver.CrawlResponse) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object html_ = "";
    /**
     * <code>optional string html = 1;</code>
     */
    public java.lang.String getHtml() {
      java.lang.Object ref = html_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        html_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string html = 1;</code>
     */
    public com.google.protobuf.ByteString
        getHtmlBytes() {
      java.lang.Object ref = html_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        html_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string html = 1;</code>
     */
    public Builder setHtml(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      html_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string html = 1;</code>
     */
    public Builder clearHtml() {
      
      html_ = getDefaultInstance().getHtml();
      onChanged();
      return this;
    }
    /**
     * <code>optional string html = 1;</code>
     */
    public Builder setHtmlBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      html_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object charset_ = "";
    /**
     * <code>optional string charset = 2;</code>
     */
    public java.lang.String getCharset() {
      java.lang.Object ref = charset_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        charset_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string charset = 2;</code>
     */
    public com.google.protobuf.ByteString
        getCharsetBytes() {
      java.lang.Object ref = charset_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        charset_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string charset = 2;</code>
     */
    public Builder setCharset(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      charset_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string charset = 2;</code>
     */
    public Builder clearCharset() {
      
      charset_ = getDefaultInstance().getCharset();
      onChanged();
      return this;
    }
    /**
     * <code>optional string charset = 2;</code>
     */
    public Builder setCharsetBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      charset_ = value;
      onChanged();
      return this;
    }

    private int httpCode_ ;
    /**
     * <code>optional sfixed32 httpCode = 3;</code>
     */
    public int getHttpCode() {
      return httpCode_;
    }
    /**
     * <code>optional sfixed32 httpCode = 3;</code>
     */
    public Builder setHttpCode(int value) {
      
      httpCode_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional sfixed32 httpCode = 3;</code>
     */
    public Builder clearHttpCode() {
      
      httpCode_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object url_ = "";
    /**
     * <code>optional string url = 4;</code>
     */
    public java.lang.String getUrl() {
      java.lang.Object ref = url_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        url_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string url = 4;</code>
     */
    public com.google.protobuf.ByteString
        getUrlBytes() {
      java.lang.Object ref = url_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        url_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string url = 4;</code>
     */
    public Builder setUrl(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      url_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string url = 4;</code>
     */
    public Builder clearUrl() {
      
      url_ = getDefaultInstance().getUrl();
      onChanged();
      return this;
    }
    /**
     * <code>optional string url = 4;</code>
     */
    public Builder setUrlBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      url_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:proxy_server.CrawlResponse)
  }

  // @@protoc_insertion_point(class_scope:proxy_server.CrawlResponse)
  private static final com.bfm.mf.crawler.proxyserver.CrawlResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.bfm.mf.crawler.proxyserver.CrawlResponse();
  }

  public static com.bfm.mf.crawler.proxyserver.CrawlResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CrawlResponse>
      PARSER = new com.google.protobuf.AbstractParser<CrawlResponse>() {
    public CrawlResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new CrawlResponse(input, extensionRegistry);
      } catch (RuntimeException e) {
        if (e.getCause() instanceof
            com.google.protobuf.InvalidProtocolBufferException) {
          throw (com.google.protobuf.InvalidProtocolBufferException)
              e.getCause();
        }
        throw e;
      }
    }
  };

  public static com.google.protobuf.Parser<CrawlResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CrawlResponse> getParserForType() {
    return PARSER;
  }

  public com.bfm.mf.crawler.proxyserver.CrawlResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
