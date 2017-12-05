P3a(client side)
1.test.xml is the valid xml file on the client side go to P3a and use that file to upload to servlet.
2.If there is an error in the java buildpath on the JRE System Libraries download the appropriate library to fix the error, DO NOT CHANGE IT NOR REMOVE IT!!!
3.Import user library which is located in the project folder.
4.When running Tomcat servlet when the option of  first and last name comes up type Henry for first name and Levin last name. (At the end of the program you will see the filepath to the merge wheteher or not you met the conditions to merge and at the end of the filepath it would say comb.xml)
5.Tomcat listens  on port 8081.
6.rmi client listens on port 2000.
7.when you've finished running the program the first file path is file to upload to tomcat servlet it ends with "test.xml".
8.when you've finished running the program the second file path is file to download from the rmi server it ends with "org.xml".
9.If you start over from the page where you uplad the file to tomcat servlet delete the uploads folder and the comb.xml and org.xml file in your project folder.

P5(server side)
1.If there is an error in the java buildpath on the JRE System Libraries download the appropriate one do not change it nor remove it
2a.go to Java compiler in preference tab and under "Compiler Compliance level" select use default compliance settings.
  b. to the right of the "Compiler Compliance level" select the highest comliest level option  ex. "if the highest 1.7 selct 1.7". ( do step b if your enviroment doesn't allow compliance level up to 1.8)
3. org.xml is the valid xml file on the server side.
4.rmi registry listens on port 1095, rmi sever listens port 2037



