/*
 * Copyright 2021 Pascal Zarrad
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Configuration of commitlint to check commit message guidelines
module.exports = {
	parserPreset: 'conventional-changelog-conventionalcommits',
	rules: {
		'subject-max-length': [2, 'always', 50],
		'subject-case': [
			2,
			'never',
			['sentence-case', 'start-case'],
		],
		'subject-empty': [2, 'never'],
		'subject-full-stop': [2, 'never', '.'],
		'type-case': [2, 'always', 'lower-case'],
		'type-empty': [2, 'never'],
		'type-enum': [
			2,
			'always',
			[
                'feat',
                'fix',
                'perf',
                'refactor',
                'cs',
                'test',
                'build',
                'ci',
                'docs',
                'changelog',
                'bump'
			],
		],
		'scope-empty': [2, 'always'],
		'header-max-length': [2, 'always', 75],
		'body-leading-blank': [1, 'always'],
		'body-max-line-length': [2, 'always', 75],
		'footer-leading-blank': [1, 'always'],
		'footer-max-line-length': [2, 'always', 75]
	},
};
