import scala.io.Source
import scala.math._
import java.io._
import smile._
import smile.data._
import smile.classification._
import javax.swing.JFrame
import javax.swing.JPanel
import smile.plot.BarPlot
import smile.plot.Palette
import scala.io.Source
import smile.plot.ScatterPlot
import smile.regression.ols
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import smile.plot.Line;
import smile.plot.LinePlot;
import smile.plot.PlotCanvas;
import smile.validation.RMSE
import java.awt.Dimension
import java.awt.Robot
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import smile.math.distance.Distance
import smile.math.kernel.PolynomialKernel
import smile.math.kernel.GaussianKernel
import smile.math.kernel.LinearKernel

object KDD_ACP {
  def main(args: Array[String]): Unit = {
    val path = "/home/user14/Documents/MachineLearning/DataTP/"
    val file_name1 = ""
    val ordi = scala.io.Source.fromFile(path + file_name1).getLines.filter(!_.isEmpty()).map(_.split("\\t"))
    var data = ordi.map { x => Array(x(0).toDouble, x(1).toDouble) }.toArray
    val x = data.map { x => Array(x(0).toDouble) }.toArray
    val y = data.map { x => x(1).toDouble }.toArray
  }
  
}