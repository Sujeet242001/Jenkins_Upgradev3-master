import jenkins.model.Jenkins;

pm = Jenkins.instance.pluginManager
uc = Jenkins.instance.updateCenter

// Calls Plugin Catalog and Download All the Information that require
pm.doCheckUpdatesServer()

// List of PlugIn with Dependencies
["github", "mstest", "workflow-aggregator", "docker-build-publish"].each {
  if (! pm.getPlugin(it)) {
    deployment = uc.getPlugin(it).deploy(true)
    deployment.get()
  }
}

// Restart Jenkins after installing plugins (optional)
Jenkins.instance.restart()

// import jenkins.model.Jenkins

// def jenkins = Jenkins.get()

// def pm = jenkins.pluginManager
// def uc = jenkins.updateCenter

// // Refresh update center metadata
// uc.updateAllSites()

// def plugins = [
//     "github",
//     "mstest",
//     "workflow-aggregator",
//     "docker-build-publish"
// ]

// plugins.each { pluginName ->
//     if (pm.getPlugin(pluginName) == null) {
//         def plugin = uc.getPlugin(pluginName)
//         if (plugin != null) {
//             println "Installing ${pluginName}..."
//             plugin.deploy(true).get()
//         } else {
//             println "Plugin ${pluginName} not found in Update Center."
//         }
//     } else {
//         println "Plugin ${pluginName} is already installed."
//     }
// }

// // Restart Jenkins if required
// jenkins.safeRestart()