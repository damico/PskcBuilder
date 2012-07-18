PskcBuilder
===========

Portable Symmetric Key Container XML Builder written in Java

A really basic implementation of RFC6030 (http://tools.ietf.org/html/rfc6030).

The idea is to build a small application that generates PSKC xml file based on a txt file with only token serial numbers and its seeds in a HEX string.

To test it, go to org.jdamico.pskcbuilder.tests.TestBuilder JUnit class