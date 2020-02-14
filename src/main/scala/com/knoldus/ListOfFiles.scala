package com.knoldus

package com.knoldus

import java.io.File
import scala.concurrent.Future
import scala.util.{Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global

object ListOfFiles {

  def main(args: Array[String]): Unit = {
    val dir = "/home/knoldus/multi-threadings/Folder1"
    val dirObj = new File(dir)
    if (dirObj.exists && dirObj.isDirectory) {
      val name = dirObj.getName
      val filesList = dirObj.listFiles.toList
      val lengthOfListFiles = filesList.length


      val listFiles: Future[Unit] = Future {
        getListOfFiles(name, filesList, lengthOfListFiles, 0)
      }
      val nanoSeccond=6000
      Thread.sleep(nanoSeccond)
      listFiles onComplete {
        case Success(file) => file
        case Failure(exception) => exception.getMessage
      }

    }

  }


  def getListOfFiles(str: String, filesList: List[File], lengthOfList: Int, index: Int): Unit = {
    if (index < lengthOfList) {
      if (filesList(index).isFile) {

        println(filesList(index).getAbsolutePath.split(str)(1))
      }
      else if (filesList(index).isDirectory) {

        getListOfFiles(str, filesList(index).listFiles.toList, filesList(index).listFiles().length, 0)


      }
      getListOfFiles(str, filesList, filesList.length, index + 1)
    }
    else {

    }


  }


}
