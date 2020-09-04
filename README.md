# Scala.js

## Set up

To create a new project:
1) `sbt new scala/scala-seed.g8` and give the project name
2) Add the Sca.ajs sbt plugin to `project/plugins.sbt`.
  ```sbt
  addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.1.1")
  ```
3) Enable the plugin in `build.sbt`
  ```sbt
  enablePlugins(ScalaJSPlugin)

  // an application with a main method
  scalaJSUseMainModuleInitializer := true
  ```
4) In `project/build.properties`, specify the sbt version - find the latest [here](https://www.scala-sbt.org/download.html)

  ```sbt
  sbt.version=1.3.13
  ```
5) To get stack traces resolved in Node.js, install the `source-map-support` package. 
  ```
  npm install source-map-support
  ```
- This is all you need to configure the build.
- For compiling and running the application, you still need to use sbt from the command line.

## Integrating with HTML

1) Generate JavaScript using sbt's `fastOptJS` task.
2) The js file can then be included in an HTML page's `script` tag.
  ```html
  <script type="text/javascript" src="./target/scala-2.13/scalajs-tutorial-fastopt.js"></script>
  ```

## Adding the DOM Library

To use the DOM, it's best to use the statically typed Scala.js DOM library.

```sbt
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.0.0"
```
Place the following content in the scala file

```scala
package tutorial.webapp

import org.scalajs.dom
import org.scalajs.dom.document

object TutorialApp {
  def main(args: Array[String]): Unit = {
    appendPar(document.body, "Hello world, from Scala!")
  }

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    parNode.textContent = text
    targetNode.appendChild(parNode)
  }
}
```

Use VSCode to run a live server targeting the HTML file that imports the built `.js` file.
```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>The Scala.js Tutorial</title>
</head>
<body>
  <!-- Include Scala.js compiled code -->
  <script type="text/javascript" src="./target/scala-2.13/scalajs-tutorial-fastopt.js"></script>
</body>
</html>
```

Finally run `~fastOptJS` to start a watcher that will compile the scala source to js.

You now have a live reload server which will recompile scala to javascript, and reload the html page to show the changes!
