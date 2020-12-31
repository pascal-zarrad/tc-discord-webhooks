# Contributing

Thank you for your interest in contributing to TeamCity Discord Webhooks - but to keep everything organized, we need some guidelines and standards that apply to the project. We tried to keep those guidelines as short as possible and make it easy for new contributors to get started. Please read these guidelines carefully, to keep up with our issue, branching and pull request standards.

## Code of conduct

Please refer to our code of conduct before you contribute anything as it sets some behavior rules to make a pleasant together. At the moment, when you contribute anything, you must follow this code of conduct.
Dealing with questions, bugs and feature requests.

If you have any questions, bugs or feature requests, feel free to open an issue. Note that you must use one of our issue templates that your issue is valid - valid issues will always retrieve the attention that is required to solve them. Before you create an issue, search for your subject and be sure to not create a duplicate. Always provide a clear and concise explanation of your matter of concern.
Security issues.

If you find some really serious security issue, please don't create a GitHub issue for it in the first line - it could put all users of TeamCity Discord Webhooks into great risk. Send a mail to p.zarrad@outlook.de instead, to keep it structured you can simply copy our bug template into your mail.

## Contributing code

Code contributions are great because they allow anyone to contribute to their favorite projects - in addition they help us to make progress. Although there are some things to keep in mind when contributing code. You grant us a perpetual license to use your code under the MIT license at the moment you contribute something to this project. This contributing code section covers all defined standards that have nothing to do with or coding standards, as they are separated from our contribution guide - but we are recommending to read them, as code that not follows them will be rejected.
First time contributors.

As a first time contributor, you might find it hard to find an issue that you can work on. To make it easier for you, some issues will be labeled as "good first issue". This type of issue can be often done with a few changes to the source code, which does not require a too deep knowledge of the projects source code.
Process of a code contribution.

The step by step process for code contributions to TeamCity Discord Webhooks looks like the following:

 - Choose an issue that you want to resolve (Create one if you want to implement something, but there is no issue for it)
 - Create a comment in that issue that you want to resolve it.
 - You can now start to develop. Though the issue will not be yours till it has been assigned to you by a collaborator or maintainer.
 - If you're done with development, create a pull request.
 - Your PR will be reviewed by a collaborator or maintainer.
 - If your PR has been approved you're done, else start again at number 3 and make the required changes.

## Guidelines for issues

The following things should be noted when working with issues:

 - Always use the matching template when you create new issues.
 - Keep the issue up to date with your current progress.
 - Do not create duplicates.
 - If you report a security issue, first visit our [security guidelines](SECURITY.md).

If you don't follow these guidelines, your issue will be closed and labeled as invalid or duplicate.

## Pull requests, branching and commits
### Branching

Our project uses a very standard branching model. 
We have the `master` branch that is always the most stable branch.
A workflow deploys the latest push to the `master` branch to the Maven central repository.
Pull requests to the `master` branch will only be accepted if they contain a version bump.
The `develop` branch is our default branch. 
All contributions will be merged into `develop` using pull requests.
A merge to the `master` branch triggers a snapshot release.
If you want to contribute, fork the project and do your changes. 
You should create a feature or bug branch when contributing. 
We do not use documentation branches to minify the overhead.

### Commits

Commit messages are great to express the intention of specific changes. In this project they are not only used
to document changes but also to auto generate our changelogs.
This means that some strict rules are necessary to ensure meaningful commit messages when contributing.

This project uses some abbreviation of the [conventional commits](https://www.conventionalcommits.org/en/v1.0.0/) standard.

Your messages must have the following format:
```
type: some imperative subject line
```

If your commit contains breaking changes:
```
type: some imperative subject line

BREAKING CHANGE: Some breaking change
```

Possible types:
 - feat: A new feature
 - fix: A bug fix
 - perf: A code change that improves performance
 - refactor: A code change that neither fixes a bug nor adds a feature
 - cs: Changes that do not affect the meaning of the code
 - test: Adding missing tests or correcting existing tests
 - build: Changes that affect the build system or external dependencies
 - ci: Changes to the CI configuration files and scripts
 - docs: Documentation only changes
 - changelog: Internally used by actions for commits that generate changelogs
 - bump: Bump version of the project

Breaking changes should be noted using a "BREAKING CHANGE:" footer.
The "!" notation must not be used to indicate a breaking change.
You can also use concrete descriptions. You must put an empty line between the subject and footer.
The subject after the double colon must start with a lower case letter.
The subject line must be written in the imperative form  (e.g. do this, make that, add something, ...).

Scopes must not be used in commit messages, therefore no scopes have been defined.

### Pull requests

When you're ready to create a pull request, use the predefined template for it. Choose a meaningful title (maybe the issues title will fit) and add all required labels. It is very important to link the right issue in the "Solves issue" section of the template, as every PR must be the result of an open issue.

Requirements for an approved PR:

 - Merge into develop: You need one approving review from a collaborator or maintainer.
 - The TravisCI checks have to pass.
 - The commit messages must follow the guidelines mentioned in this document.
 - All requested changes and conversations have to be resolved before the merge.
 - You need a linked issue that the PR solves.
 - Merge into master: Only develop is allowed to be merged into master.
 - Merge into master: You need two approving reviews. One must be from a maintainer.

## You're ready

As you read this contribution guidelines, the code of conduct, and most important our coding guidelines you are good to go! We really appreciate your work spent on contributions to our project, as people like you make the open source community great ❤️!

If you have some suggestions to this contributing guidelines, feel free to suggest them!
