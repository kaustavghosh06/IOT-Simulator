object Upper {

  def main(args: Array[String])= {
    
     args.map(_.toUpperCase()).foreach(println("%s ",_))     
     println("")
  }

}