Localization
============

Unless you are a native English speaker, you may like to run Micropolis
in your own language. Micropolis is designed to be run in any language,
but we need translators to provide the translated text to display.

1. Open a command prompt window and `cd` to the directory containing the `micropolis.jar` file.

2. Run the following command to launch the translation tool: 

    `java -cp micropolisj.jar micropolisj.util.TranslationTool`

3. Click `Add Locale` and enter the appropriate 2-character language code, and (optionally) a 2-character country code.

4. Next to each string, double-click the blank field and type in a translation.

5. Click `Save` which his will cause the translated strings to be written in the proper format into a subdirectory named
 micropolisj.

6. Close the translation tool. Push the generated files (from the micropolisj subdirectory) to the repository for inclusion
 in the next release of Micropolis.
