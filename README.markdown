This GWT module reproduces what looks like a Safari/JavaScriptCore bug when the module
is compiled without -draftCompile. Compilation style (i.e obfuscated/pretty/detailed) does
make a difference - obfuscated fails faster than detailed & pretty but all fail.

This problem manifests itself on Safari/Lion 5.1.4, 5.1.4 but apparently not 5.1.2
