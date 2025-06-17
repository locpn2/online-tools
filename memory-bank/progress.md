# Progress

**What works:**

*   The application starts successfully.
*   Git repository initialized and pushed to remote.
*   Cline memory-bank initialized.
*   Unit tests implemented for HashController.

**What's left to build:**

*   Implement Excel to XML, JSON, CSV converter.
*   Implement unit tests for the remaining API controllers.

**Current status:**

*   Unit tests for HashController are passing.

**Known issues:**

*   None.

**Evolution of project decisions:**

*   Initially attempted to use the GitHub MCP server to create the repository, but encountered authentication issues.
*   Switched to manually creating the repository and providing the URL.
*   Replaced HexFormat with String.format for Java version compatibility.
*   Used SHA3-256 instead of Keccak-256 for the keccak256Hash method.
