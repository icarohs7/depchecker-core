/*
 *  MIT License
 *
 * Copyright (c) 2018.  Icaro R D Temponi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.icarohs7.entities

import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class ArtifactTest {

    @Test
    fun `should create an artifact by its group and id`() {
        //Arrange
        val artifact = Artifact(group = "com.github.icarohs7", artifactId = "unoxkcommons")

        //Assert
        artifact.group shouldEqual "com.github.icarohs7"
        artifact.artifactId shouldEqual "unoxkcommons"
    }

    @Test
    fun `should create an artifact by its group and id in a single string`() {
        //Arrange
        val artifact = Artifact("com.github.icarohs7:unoxkcommons")

        //Assert
        artifact.group shouldEqual "com.github.icarohs7"
        artifact.artifactId shouldEqual "unoxkcommons"
    }

    @Test
    fun `should convert and artefact to string`() {
        //Arrange
        val artifact = Artifact(group = "com.github.icarohs7", artifactId = "unoxkcommons")

        //Assert
        artifact.toString() shouldEqual "com.github.icarohs7:unoxkcommons"
    }
}