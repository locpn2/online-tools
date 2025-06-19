# Progress

**What works:**

*   The application starts successfully.
*   Git repository initialized and pushed to remote.
*   Cline memory-bank initialized.
*   Unit tests implemented for HashController.
*   Unit tests implemented for GenerationController (UUID and QR Code).
*   Unit tests implemented for HashController (MD5, SHA256, SHA512, Keccak256).
*   Unit tests implemented for ExcelConverterService.
*   Implement Excel to XML, JSON, CSV converter.
*   Unit tests implemented for EncodeController, ExcelConverterController, and FormatController.

**What's left to build:**

**Completed:**

*   Implement Công cụ tạo UUID.
*   Implement unit tests for the remaining API controllers.
*   Implement Công cụ chuyển đổi định dạng văn bản.
*   Implement Công cụ mã hóa/giải mã Base64.
*   Implement Công cụ tạo mật khẩu ngẫu nhiên.

**Current status:**

*   Unit tests for HashController are passing.
*   Unit tests for GenerationController (UUID and QR Code) are passing.
*   Unit tests for HashController (MD5, SHA256, SHA512, Keccak256) are passing.

**Known issues:**

*   None.

**Evolution of project decisions:**

*   Initially attempted to use the GitHub MCP server to create the repository, but encountered authentication issues.
*   Switched to manually creating the repository and providing the URL.
*   Replaced HexFormat with String.format for Java version compatibility.
*   Used SHA3-256 instead of Keccak-256 for the keccak256Hash method.
