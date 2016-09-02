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
import java.lang.ProcessBuilder$Redirect.Type

object KDD_ACP {
  def main(args: Array[String]): Unit = {
    val path = "/home/user14/Documents/MachineLearning/DataTP/KDDCup1999Data/"
    val file_name1 = "kddcup.data_10_percent_corrected"
    val file_name2 = "kddcup.names"
    val data_source = scala.io.Source.fromFile(path + file_name1).getLines.filter(!_.isEmpty()).map(_.split(",")).toArray
    val label_source = scala.io.Source.fromFile(path + file_name2).getLines.drop(1).map(_.split(":")(0)).toArray :+ "label"
    //    val start = 400000
    //    val end = 400020
    //    for (i <- start to end - 1) {
    //      println(data_source.toList(i).toList)
    //      //println("boop")
    //    }
    //println(label_source.toList)
    // duration, protocol_type, service, flag, src_bytes, dst_bytes, land, wrong_fragment, urgent, hot, num_failed_logins, logged_in, num_compromised, root_shell, su_attempted, num_root, num_file_creations, num_shells, num_access_files, num_outbound_cmds, is_host_login, is_guest_login, count, srv_count, serror_rate, srv_serror_rate, rerror_rate, srv_rerror_rate, same_srv_rate, diff_srv_rate, srv_diff_host_rate, dst_host_count, dst_host_srv_count, dst_host_same_srv_rate, dst_host_diff_srv_rate, dst_host_same_src_port_rate, dst_host_srv_diff_host_rate, dst_host_serror_rate, dst_host_srv_serror_rate, dst_host_rerror_rate, dst_host_srv_rerror_rate, label

    //source.foreach { x => println(x.toList+" "+x.toList.size) }
    //var data_source = ordi.map { x => Array(x(0).toDouble, x(1).toDouble) }.toArray
    val lineNb = data_source.size
    val absc = (1 to lineNb).toArray.toIterator
    val var_nb = 0
    //val x = data_source.map { x => Array(x(var_nb).toDouble, absc.next().toDouble) }.toArray
    val x = data_source.map { x => x(var_nb).toDouble }.toArray
    //val y = data_source.map { x => x(1).toDouble }.toArray

    //println(lineNb+" "+x.toList)
    //val plot1 = smile.plot.plot(x, '.')
    // 1 - stocker les valeurs des variables non numériques possibles, avec un groupe by, unique
    // 2 - remplacer ces valeurs par des étiquettes numériques
    // 3- on peut alors faire le boxplot également pour ces variables.
    val data0 = data_source.map { x => x.map { x => if(x.forall(_.isDigit)){x.toDouble} }.filter { x => x.getClass() == Double}}
    val data = data0.map { x => x.map { x => x.toDouble } }
    data.foreach { x => println(x.toList) }
//    val plot1 = smile.plot.boxplot(data, label_source)
//    plot1.setVisible(true)
//    plot1.setSize(new Dimension(1000, 1000))
    //plot1.
    //    plot1.canvas.setTitle(label_source(var_nb)+"(line_nb)")
    //    plot1.canvas.setAxisLabels ("line_nb", label_source(var_nb))
    println("boop")
  }

}