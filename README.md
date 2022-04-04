# conveniencelib

A convenient library for android development

## Installation
Add the dependency to your `build.gradle(.kts)` file:
<table>
  <thead>
    <tr>
      <th>Kotlin</th>
      <th>Groovy</th>
    </tr>
  </thead>
  <tr>
    <td>
<pre lang="kotlin">dependencies {
    // ...
    implementation("io.posidon:android.conveniencelib:22.0")
}</pre>
    </td>
    <td>
<pre lang="groovy">dependencies {
    // ...
    implementation 'io.posidon:android.conveniencelib:22.0'
}</pre>
    </td>
  </tr>
</table>

And also make sure that the `jitpack.io` repository is included
<table>
  <thead>
    <tr>
      <th>Kotlin</th>
      <th>Groovy</th>
    </tr>
  </thead>
  <tr>
    <td>
<pre lang="kotlin">allprojects {
    repositories {
        // ...
        maven {
            url = uri("https://jitpack.io")
        }
    }
}</pre>
    </td>
    <td>
<pre lang="groovy">allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}</pre>
    </td>
  </tr>
</table>
