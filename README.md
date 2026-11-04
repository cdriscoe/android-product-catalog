# Android Product Catalog and Sales Calculator

**University coursework (2019) · Java · Android SDK · SQLite**

An academic Android application that maintains a local catalog of candies/products and prices. Users can add, edit and remove catalog entries, select products from a dynamically constructed grid, and see a running purchase total.

## What the project demonstrates
- Java classes and Android activity-based user interfaces
- Persistent storage through `SQLiteOpenHelper` and SQL operations
- Add, list, update and delete operations for product records
- Event handling, dynamic UI construction and currency formatting

## Where to look
- `app/src/main/java/com/example/productcatalog/MainActivity.java` — product selection UI and total calculation
- `app/src/main/java/com/example/productcatalog/DatabaseManager.java` — database schema and operations
- `app/src/main/java/com/example/productcatalog/InsertActivity.java` — adding products
- `app/src/main/java/com/example/productcatalog/UpdateActivity.java` — editing products
- `app/src/main/java/com/example/productcatalog/DeleteActivity.java` — removing products

## Build status / historical context

This is **archived university code**, not a newly developed or currently supported Android product. It uses an older Android Support Library, Android Gradle Plugin 3.3.0, SDK 28, Gradle 4.10.1 and `jcenter()` repository settings. It has **not been rebuilt or tested on a modern Android Studio release**. Updating the Gradle tooling and Android dependencies would be necessary before relying on it as a modern demo.

### Known improvements for a future revision
- Replace SQL string concatenation in `DatabaseManager` with bound parameters / `ContentValues`.
- Audit cursor and database-resource handling and input validation.
- Migrate obsolete Android Support Library dependencies to AndroidX.
- Add meaningful automated tests and screenshots from a verified running build.

## Portfolio notes

The original university student identifier was removed from the Java package name and corresponding project settings for privacy. No application behavior was intentionally changed. The academic project is preserved for source-code review; compilation and runtime functionality are not asserted.