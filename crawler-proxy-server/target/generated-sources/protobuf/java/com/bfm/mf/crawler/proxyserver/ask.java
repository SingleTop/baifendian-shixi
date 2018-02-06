// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proxyentry.proto

package com.bfm.mf.crawler.proxyserver;

/**
 * Protobuf type {@code proxy_server.ask}
 *
 * <pre>
 *询问是否有资源
 * </pre>
 */
public  final class ask extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:proxy_server.ask)
    askOrBuilder {
  // Use ask.newBuilder() to construct.
  private ask(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private ask() {
    askMessage_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private ask(
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

            askMessage_ = s;
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
    return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_ask_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_ask_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.bfm.mf.crawler.proxyserver.ask.class, com.bfm.mf.crawler.proxyserver.ask.Builder.class);
  }

  public static final int ASKMESSAGE_FIELD_NUMBER = 1;
  private volatile java.lang.Object askMessage_;
  /**
   * <code>optional string askMessage = 1;</code>
   */
  public java.lang.String getAskMessage() {
    java.lang.Object ref = askMessage_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      askMessage_ = s;
      return s;
    }
  }
  /**
   * <code>optional string askMessage = 1;</code>
   */
  public com.google.protobuf.ByteString
      getAskMessageBytes() {
    java.lang.Object ref = askMessage_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      askMessage_ = b;
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
    if (!getAskMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, askMessage_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getAskMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, askMessage_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.bfm.mf.crawler.proxyserver.ask parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.bfm.mf.crawler.proxyserver.ask parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.bfm.mf.crawler.proxyserver.ask parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.bfm.mf.crawler.proxyserver.ask parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.bfm.mf.crawler.proxyserver.ask parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.bfm.mf.crawler.proxyserver.ask parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.bfm.mf.crawler.proxyserver.ask parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.bfm.mf.crawler.proxyserver.ask parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.bfm.mf.crawler.proxyserver.ask parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.bfm.mf.crawler.proxyserver.ask parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.bfm.mf.crawler.proxyserver.ask prototype) {
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
   * Protobuf type {@code proxy_server.ask}
   *
   * <pre>
   *询问是否有资源
   * </pre>
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:proxy_server.ask)
      com.bfm.mf.crawler.proxyserver.askOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_ask_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_ask_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.bfm.mf.crawler.proxyserver.ask.class, com.bfm.mf.crawler.proxyserver.ask.Builder.class);
    }

    // Construct using com.bfm.mf.crawler.proxyserver.ask.newBuilder()
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
      askMessage_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.bfm.mf.crawler.proxyserver.ProxyServerProto.internal_static_proxy_server_ask_descriptor;
    }

    public com.bfm.mf.crawler.proxyserver.ask getDefaultInstanceForType() {
      return com.bfm.mf.crawler.proxyserver.ask.getDefaultInstance();
    }

    public com.bfm.mf.crawler.proxyserver.ask build() {
      com.bfm.mf.crawler.proxyserver.ask result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.bfm.mf.crawler.proxyserver.ask buildPartial() {
      com.bfm.mf.crawler.proxyserver.ask result = new com.bfm.mf.crawler.proxyserver.ask(this);
      result.askMessage_ = askMessage_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.bfm.mf.crawler.proxyserver.ask) {
        return mergeFrom((com.bfm.mf.crawler.proxyserver.ask)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.bfm.mf.crawler.proxyserver.ask other) {
      if (other == com.bfm.mf.crawler.proxyserver.ask.getDefaultInstance()) return this;
      if (!other.getAskMessage().isEmpty()) {
        askMessage_ = other.askMessage_;
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
      com.bfm.mf.crawler.proxyserver.ask parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.bfm.mf.crawler.proxyserver.ask) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object askMessage_ = "";
    /**
     * <code>optional string askMessage = 1;</code>
     */
    public java.lang.String getAskMessage() {
      java.lang.Object ref = askMessage_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        askMessage_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string askMessage = 1;</code>
     */
    public com.google.protobuf.ByteString
        getAskMessageBytes() {
      java.lang.Object ref = askMessage_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        askMessage_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string askMessage = 1;</code>
     */
    public Builder setAskMessage(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      askMessage_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string askMessage = 1;</code>
     */
    public Builder clearAskMessage() {
      
      askMessage_ = getDefaultInstance().getAskMessage();
      onChanged();
      return this;
    }
    /**
     * <code>optional string askMessage = 1;</code>
     */
    public Builder setAskMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      askMessage_ = value;
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


    // @@protoc_insertion_point(builder_scope:proxy_server.ask)
  }

  // @@protoc_insertion_point(class_scope:proxy_server.ask)
  private static final com.bfm.mf.crawler.proxyserver.ask DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.bfm.mf.crawler.proxyserver.ask();
  }

  public static com.bfm.mf.crawler.proxyserver.ask getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ask>
      PARSER = new com.google.protobuf.AbstractParser<ask>() {
    public ask parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new ask(input, extensionRegistry);
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

  public static com.google.protobuf.Parser<ask> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ask> getParserForType() {
    return PARSER;
  }

  public com.bfm.mf.crawler.proxyserver.ask getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

