PskcBuilder
===========

"Portable Symmetric Key Container" XML Builder written in Java

Author: Jose Damico (jd.comment@gmail.com)

A really basic implementation of RFC6030 (http://tools.ietf.org/html/rfc6030). It supports both hotp and totp.

The idea is to build a small application that generates PSKC xml file based on a txt file with only token serial numbers and its seeds in a HEX string.

To test it, go to org.jdamico.pskcbuilder.tests.TestBuilder JUnit class.

To run it go to org.jdamico.pskcbuilder.runtime.Builder class. It opens a GUI with the following options:

1) INPUT		Button:		To select a text file with serials/seeds; 
2) OUTPUT		Button:		To select a folder where a output pskc file will be created;
3) ALGORITHM 	ComboBox:	To select between hotp/totp (per event or per time);
4) COLUMN		TextField:	To select the columns separator (example ; or : or ,) of input text file;
5) MANUFACTURER	TextField:	To type the name of token manufacturer;
6) RESP.LENGTH	TextField:	To type the OTP response lenght (example: 4,6,8);
7) COUNTER		TextField:	This element contains the event counter for event-based (hotp) OTP algorithms;
8) INTERVAL		TextField:	This element contains the time (in seconds) for time-based OTP algorithms (totp) (example: 30,60);  
9) BUILD		Button:		To start the creation of pskc file.

The entire code was written as a project of Eclipse Indigo (http://www.eclipse.org).

----------------------
Eclipse Public License - v 1.0 (http://www.eclipse.org/legal/epl-v10.html)
