// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proxyentry.proto

package com.bfm.mf.crawler.proxyserver;

public interface CrawlRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:proxy_server.CrawlRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string url = 1;</code>
   */
  java.lang.String getUrl();
  /**
   * <code>optional string url = 1;</code>
   */
  com.google.protobuf.ByteString
      getUrlBytes();

  /**
   * <code>optional string ip = 2;</code>
   */
  java.lang.String getIp();
  /**
   * <code>optional string ip = 2;</code>
   */
  com.google.protobuf.ByteString
      getIpBytes();

  /**
   * <code>optional string port = 3;</code>
   */
  java.lang.String getPort();
  /**
   * <code>optional string port = 3;</code>
   */
  com.google.protobuf.ByteString
      getPortBytes();

  /**
   * <code>optional string header = 4;</code>
   */
  java.lang.String getHeader();
  /**
   * <code>optional string header = 4;</code>
   */
  com.google.protobuf.ByteString
      getHeaderBytes();
}