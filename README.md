# Splunk Tracer Common

[ ![Download](https://api.bintray.com/packages/splunk/maven/java-common/images/download.svg) ](https://bintray.com/splunk/maven/) [![MIT license](http://img.shields.io/badge/license-MIT-blue.svg)](http://opensource.org/licenses/MIT)

The core Splunk distributed tracing library for the Java runtime environment. For specific documentation
see [splunk-tracer-java](https://github.com/splunk/splunk-tracer-java) or
[splunk-tracer-android](https://github.com/splunk/splunk-tracer-android).

## common

Contains the `com.splunk.tracer.shared` (shared logic) and `com.splunk.tracer.grpc` (shared compiled proto files) source files and assets for the JRE and Android libraries.

## example

Contains an example implementation of the tracer (used for integration testing).

## okhttp

Contains the transport layer specific to the okhttp flavor of the splunk tracer.

## Development info

See [DEV.md](DEV.md) for information on contributing to this instrumentation library.
