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
import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class MavenCentralRepositoryImplTest {
    private val artifact = Artifact("com.github.icarohs7.unoxlib:UNoxLib")

    @Test
    fun `should get the list of versions of an artifact from maven central`() {
        //Arrange
        val expectedList = listOf("0.3.1", "0.3.0", "0.2.4", "0.2.3", "0.2.0", "0.1.1")

        //Act
        val versions = MavenCentralRepositoryImpl.getAllVersions(artifact)
        val versionsExt = artifact.getAllVersionsAt(MavenCentralRepositoryImpl)

        //Assert
        versions shouldEqual expectedList
        versionsExt shouldEqual expectedList
    }

    @Test
    fun `should get the most recent version of an artifact from maven central`() {
        //Arrange
        val versionExt = artifact.getLastVersionAt(MavenCentralRepositoryImpl)

        //Act
        val version = MavenCentralRepositoryImpl.getLastVersion(artifact)

        //Assert
        version shouldEqual "0.3.1"
        versionExt shouldEqual "0.3.1"
    }

    @Test
    fun `should return an empty list when artifact can't be found`() {
        //Arrange
        val artifact2 = Artifact("thisshould:notexist and spaces are not valid anyway")

        //Assert
        MavenCentralRepositoryImpl.getAllVersions(artifact2) shouldEqual emptyList()
        artifact2.getAllVersionsAt(MavenCentralRepositoryImpl) shouldEqual emptyList()
    }

    @Test
    fun `should return null when artifact can't be found`() {
        //Arrange
        val artifact2 = Artifact("thisshould:notexist and spaces are not valid anyway")

        //Assert
        MavenCentralRepositoryImpl.getLastVersion(artifact2) shouldEqual null
        artifact2.getLastVersionAt(MavenCentralRepositoryImpl) shouldEqual null
    }
}