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

package com.github.icarohs7.repositories

import com.github.icarohs7.entities.Artifact
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class MavenCentralRepositoryImplTest : StringSpec() {
    init {
        val artifact = Artifact("com.github.icarohs7.unoxlib:UNoxLib")
        
        "should get the list of versions of an artifact from maven central" {
            val versions = MavenCentralRepositoryImpl.getAllVersions(artifact)
            val versionsExt = artifact.getAllVersionsAt(MavenCentralRepositoryImpl)
            val expectedList = listOf("0.3.1", "0.3.0", "0.2.4", "0.2.3", "0.2.0", "0.1.1")
            versions shouldBe expectedList
            versionsExt shouldBe expectedList
        }
        
        "should get the most recent version of an artifact from maven central" {
            val version = MavenCentralRepositoryImpl.getLastVersion(artifact)
            val versionExt = artifact.getLastVersionAt(MavenCentralRepositoryImpl)
            version shouldBe "0.3.1"
            versionExt shouldBe "0.3.1"
        }
        
        "should return an empty list when artifact can't be found" {
            val artifact2 = Artifact("thisshould:notexist and spaces are not valid anyway")
            MavenCentralRepositoryImpl.getAllVersions(artifact2) shouldBe emptyList()
            artifact2.getAllVersionsAt(MavenCentralRepositoryImpl) shouldBe emptyList()
        }
        
        "should return null when artifact can't be found" {
            val artifact2 = Artifact("thisshould:notexist and spaces are not valid anyway")
            MavenCentralRepositoryImpl.getLastVersion(artifact2) shouldBe null
            artifact2.getLastVersionAt(MavenCentralRepositoryImpl) shouldBe null
        }
    }
}