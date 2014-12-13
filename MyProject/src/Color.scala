object Color {
	  val BLACK = java.awt.Color.BLACK
	  val DARK_GRAY = java.awt.Color.DARK_GRAY
	  val GRAY = java.awt.Color.GRAY
	  val LIGHT_GRAY = java.awt.Color.LIGHT_GRAY
	  val WHITE = java.awt.Color.WHITE
	 
	  val RED = java.awt.Color.RED
	  val PINK = java.awt.Color.PINK
	  val ORANGE = java.awt.Color.ORANGE
	  val YELLOW = java.awt.Color.YELLOW
	  val GREEN = java.awt.Color.GREEN
	  val CYAN = java.awt.Color.CYAN
	  val BLUE = java.awt.Color.BLUE
	  val MAGENTA = java.awt.Color.MAGENTA
	 
	  val CLEAR = RGBA(0, 0, 0, 0)
	}
	
	object RGB {
	  import java.awt.Color
	 
	  def apply(r : Int, g : Int, b : Int) : Color =
	    new Color(r, g, b)
	}
	
	object RGBA {
	  import java.awt.Color
	 
	  def apply(r : Int, g : Int, b : Int, alpha : Int) : Color =
	    new Color(r, g, b, alpha)
	}
	
	object HSV {
	  def apply(hue : Double, saturation : Double, value : Double) : java.awt.Color =
	    java.awt.Color.getHSBColor(hue.toFloat, saturation.toFloat, value.toFloat)
	}
	
	object HSVA {
	  def apply(hue : Double, saturation : Double, value : Double, alpha : Double) : java.awt.Color = {
	    val base = HSV(hue, saturation, value)
	    new java.awt.Color(base.getRed, base.getGreen, base.getBlue, (alpha * 255).toInt)
	  }
	}