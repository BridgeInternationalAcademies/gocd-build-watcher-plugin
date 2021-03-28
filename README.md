# gocd-build-watcher-plugin
A GoCD notification plugin which sends direct emails and Slack messages to the person who breaks a build
![Screenshot Fail](static/screenshot-fail.png) ![Screenshot Fixed](static/screenshot-fixed.png)

## Configuration

### Accesing pluging Settings (on GoCD)
![Settings 1](static/settings1.png)
![Settings 2](static/settings2.png)

### Access Go Server API
This plugin requires access to Go API to fetch status of previous job execution and material changes.
If you are enforced to login before you can access the main pipelines screen, you need to provide a valid user/password to our plugin.
1. Enter [plugin settings](#access-go-server-api)
2. Enter API username and password:
![Settings 3](static/settings3.png)

### Adding Slack notifications
As this pluging needs to resolve user's email into a Slack ID, a *WebHook* is not enough to work.
You need to install our Slack App into your team and get an API token.
> ![Profile Warning](static/profile-warning.png)<br>
> You may see this warning when authorizing our app to work with your team.
> We will only access your team profile in order to match a email from a Material change into a Slack ID
1. Install our app into your team: <br>[![Add to Slack](https://platform.slack-edge.com/img/add_to_slack.png)](https://slack.com/oauth/authorize?&client_id=170776918258.170870737557&scope=chat:write:bot,users:read.email,users:read)
2. Copy your Slack API Token into GoCD's plugin configuration
3. (optional) Configure a custom channel or slack id for the bot. If a channel is set, messages will still @mention a user. If not set, commits with an author that can't be resolved to a user in Slack will be posted to #general by default. Enter [plugin settings](#access-go-server-api) and then:
![Settings 4](static/settings4.png)

### Adding Email notifications
1. Configure your SMTP server details
2. If you SMTP requires authentication, provide your account/password
3. Provide a From email, the one our plugin will be sending email on behalf
4. Provide an optional CC, this email will copied every time an email is sent

## Customization
- **Pipeline Broken Message**: sent to a user when a specific pipeline is broken (the build fails) by the last commit
- **Pipeline Still Broken Message**: sent to a user when a specific pipeline was broken and the last commit didn' fix it
- **Pipeline Fixed Message**: sent to a user when a specific pipeline was broken the last commit fixed it

## Installation

To install the plugin you must first clone and build the repository.  Once built:
1. Copy \plugin\build\libs\build-watcher-plugin-<version>.jar to \<gocd-installation-folder>\plugins\external

# Development

This repository is forked from: https://github.com/gmazzo/gocd-build-watcher-plugin

Modifications:
1. Uses gradle 6.8.
2. Targets GoCD 2.1.2.
3. Changes regex to extract emails from the git usernme.

### Building

To build the repository:
    `./gradlew assemble`

To clean and rebuild:
    `./gradlew clean assemble`

### Testing

To execute tests:
    `./gradlew test`

### Changing the version

To modify the package details, modify variables in /plugin/build.grade

### To Do

The repository is incomplete:
- Tests are failing due to a lack of mocking framework.
- Lacking tests in key areas.
- Dependencies are out of date.