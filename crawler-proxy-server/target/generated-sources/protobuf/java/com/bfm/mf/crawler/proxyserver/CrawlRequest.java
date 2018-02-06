// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proxyentry.proto

package com.bfm.mf.crawler.proxyserver;

/**
 * Protobuf type {@code proxy_server.CrawlRequest}
 *
 * <pre>
 *请求参数
 * </pre>
 */
public  final class CrawlRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:proxy_server.CrawlRequest)
    CrawlRequestOrBuilder {
  // Use CrawlRequest.newBuilder() to construct.
  private CrawlRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private CrawlRequest() {
    url_ = "";
    ip_ = "";
    port_ = "";
    header_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private CrawlRequest(
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

            url_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            ip_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            port_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            header_ = s;
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
    return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_CrawlRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_CrawlRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.bfm.mf.crawler.proxyserver.CrawlRequest.class, com.bfm.mf.crawler.proxyserver.CrawlRequest.Builder.class);
  }

  public static final int URL_FIELD_NUMBER = 1;
  private volatile java.lang.Object url_;
  /**
   * <code>optional string url = 1;</code>
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
   * <code>optional string url = 1;</code>
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

  public static final int IP_FIELD_NUMBER = 2;
  private volatile java.lang.Object ip_;
  /**
   * <code>optional string ip = 2;</code>
   */
  public java.lang.String getIp() {
    java.lang.Object ref = ip_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      ip_ = s;
      return s;
    }
  }
  /**
   * <code>optional string ip = 2;</code>
   */
  public com.google.protobuf.ByteString
      getIpBytes() {
    java.lang.Object ref = ip_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      ip_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PORT_FIELD_NUMBER = 3;
  private volatile java.lang.Object port_;
  /**
   * <code>optional string port = 3;</code>
   */
  public java.lang.String getPort() {
    java.lang.Object ref = port_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      port_ = s;
      return s;
    }
  }
  /**
   * <code>optional string port = 3;</code>
   */
  public com.google.protobuf.ByteString
      getPortBytes() {
    java.lang.Object ref = port_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      port_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int HEADER_FIELD_NUMBER = 4;
  private volatile java.lang.Object header_;
  /**
   * <code>optional string header = 4;</code>
   */
  public java.lang.String getHeader() {
    java.lang.Object ref = header_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      header_ = s;
      return s;
    }
  }
  /**
   * <code>optional string header = 4;</code>
   */
  public com.google.protobuf.ByteString
      getHeaderBytes() {
    java.lang.Object ref = header_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      header_ = b;
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
    if (!getUrlBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, url_);
    }
    if (!getIpBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessage.writeString(output, 2, ip_);
    }
    if (!getPortBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessage.writeString(output, 3, port_);
    }
    if (!getHeaderBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessage.writeString(output, 4, header_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getUrlBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, url_);
    }
    if (!getIpBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, ip_);
    }
    if (!getPortBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(3, port_);
    }
    if (!getHeaderBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(4, header_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.bfm.mf.crawler.proxyserver.CrawlRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.bfm.mf.crawler.proxyserver.CrawlRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.bfm.mf.crawler.proxyserver.CrawlRequest prototype) {
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
   * Protobuf type {@code proxy_server.CrawlRequest}
   *
   * <pre>
   *请求参数
   * </pre>
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:proxy_server.CrawlRequest)
      com.bfm.mf.crawler.proxyserver.CrawlRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_CrawlRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_CrawlRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.bfm.mf.crawler.proxyserver.CrawlRequest.class, com.bfm.mf.crawler.proxyserver.CrawlRequest.Builder.class);
    }

    // Construct using com.bfm.mf.crawler.proxyserver.CrawlRequest.newBuilder()
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
      url_ = "";

      ip_ = "";

      port_ = "";

      header_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_CrawlRequest_descriptor;
    }

    public com.bfm.mf.crawler.proxyserver.CrawlRequest getDefaultInstanceForType() {
      return com.bfm.mf.crawler.proxyserver.CrawlRequest.getDefaultInstance();
    }

    public com.bfm.mf.crawler.proxyserver.CrawlRequest build() {
      com.bfm.mf.crawler.proxyserver.CrawlRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.bfm.mf.crawler.proxyserver.CrawlRequest buildPartial() {
      com.bfm.mf.crawler.proxyserver.CrawlRequest result = new com.bfm.mf.crawler.proxyserver.CrawlRequest(this);
      result.url_ = url_;
      result.ip_ = ip_;
      result.port_ = port_;
      result.header_ = header_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.bfm.mf.crawler.proxyserver.CrawlRequest) {
        return mergeFrom((com.bfm.mf.crawler.proxyserver.CrawlRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.bfm.mf.crawler.proxyserver.CrawlRequest other) {
      if (other == com.bfm.mf.crawler.proxyserver.CrawlRequest.getDefaultInstance()) return this;
      if (!other.getUrl().isEmpty()) {
        url_ = other.url_;
        onChanged();
      }
      if (!other.getIp().isEmpty()) {
        ip_ = other.ip_;
        onChanged();
      }
      if (!other.getPort().isEmpty()) {
        port_ = other.port_;
        onChanged();
      }
      if (!other.getHeader().isEmpty()) {
        header_ = other.header_;
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
      com.bfm.mf.crawler.proxyserver.CrawlRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.bfm.mf.crawler.proxyserver.CrawlRequest) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object url_ = "";
    /**
     * <code>optional string url = 1;</code>
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
     * <code>optional string url = 1;</code>
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
     * <code>optional string url = 1;</code>
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
     * <code>optional string url = 1;</code>
     */
    public Builder clearUrl() {
      
      url_ = getDefaultInstance().getUrl();
      onChanged();
      return this;
    }
    /**
     * <code>optional string url = 1;</code>
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

    private java.lang.Object ip_ = "";
    /**
     * <code>optional string ip = 2;</code>
     */
    public java.lang.String getIp() {
      java.lang.Object ref = ip_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        ip_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string ip = 2;</code>
     */
    public com.google.protobuf.ByteString
        getIpBytes() {
      java.lang.Object ref = ip_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        ip_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string ip = 2;</code>
     */
    public Builder setIp(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      ip_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string ip = 2;</code>
     */
    public Builder clearIp() {
      
      ip_ = getDefaultInstance().getIp();
      onChanged();
      return this;
    }
    /**
     * <code>optional string ip = 2;</code>
     */
    public Builder setIpBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      ip_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object port_ = "";
    /**
     * <code>optional string port = 3;</code>
     */
    public java.lang.String getPort() {
      java.lang.Object ref = port_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        port_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string port = 3;</code>
     */
    public com.google.protobuf.ByteString
        getPortBytes() {
      java.lang.Object ref = port_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        port_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string port = 3;</code>
     */
    public Builder setPort(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      port_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string port = 3;</code>
     */
    public Builder clearPort() {
      
      port_ = getDefaultInstance().getPort();
      onChanged();
      return this;
    }
    /**
     * <code>optional string port = 3;</code>
     */
    public Builder setPortBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      port_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object header_ = "";
    /**
     * <code>optional string header = 4;</code>
     */
    public java.lang.String getHeader() {
      java.lang.Object ref = header_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        header_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string header = 4;</code>
     */
    public com.google.protobuf.ByteString
        getHeaderBytes() {
      java.lang.Object ref = header_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        header_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string header = 4;</code>
     */
    public Builder setHeader(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      header_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string header = 4;</code>
     */
    public Builder clearHeader() {
      
      header_ = getDefaultInstance().getHeader();
      onChanged();
      return this;
    }
    /**
     * <code>optional string header = 4;</code>
     */
    public Builder setHeaderBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      header_ = value;
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


    // @@protoc_insertion_point(builder_scope:proxy_server.CrawlRequest)
  }

  // @@protoc_insertion_point(class_scope:proxy_server.CrawlRequest)
  private static final com.bfm.mf.crawler.proxyserver.CrawlRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.bfm.mf.crawler.proxyserver.CrawlRequest();
  }

  public static com.bfm.mf.crawler.proxyserver.CrawlRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CrawlRequest>
      PARSER = new com.google.protobuf.AbstractParser<CrawlRequest>() {
    public CrawlRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new CrawlRequest(input, extensionRegistry);
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

  public static com.google.protobuf.Parser<CrawlRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CrawlRequest> getParserForType() {
    return PARSER;
  }

  public com.bfm.mf.crawler.proxyserver.CrawlRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

