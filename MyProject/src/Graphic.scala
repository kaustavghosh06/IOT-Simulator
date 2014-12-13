trait Graphic {
	  def render(gc : GraphicsContext)
	 
	  def |(g : Graphic) : Graphic = g match {
	    case over : Over => Over(List(this) ++ over.children : _*)
	    case _ => Over(this, g)
	  }
	}