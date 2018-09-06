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

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ArtifactTest : StringSpec() {
    init {
        "should create an artifact by its group and id" {
            val artifact = Artifact(group = "com.github.icarohs7", artifactId = "unoxkcommons")
            artifact.group shouldBe "com.github.icarohs7"
            artifact.artifactId shouldBe "unoxkcommons"
        }
        
        "should create an artifact by its group and id in a single string" {
            val artifact = Artifact("com.github.icarohs7:unoxkcommons")
            artifact.group shouldBe "com.github.icarohs7"
            artifact.artifactId shouldBe "unoxkcommons"
        }
        
        "should convert and artefact to string" {
            val artifact = Artifact(group = "com.github.icarohs7", artifactId = "unoxkcommons")
            artifact.toString() shouldBe "com.github.icarohs7:unoxkcommons"
        }
    }
}