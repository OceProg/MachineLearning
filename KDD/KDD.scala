import java.util.zip.GZIPInputStream

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
import java.awt.Color
import java.awt.GridLayout
import javax.swing.JFrame
import javax.swing.JPanel
import smile.plot.Line
import smile.plot.LinePlot
import smile.plot.PlotCanvas
import smile.validation.RMSE
import java.awt.Dimension
import java.awt.Robot
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import smile.math.distance.Distance
import smile.math.kernel.PolynomialKernel
import smile.math.kernel.GaussianKernel
import smile.math.kernel.LinearKernel

object KDD_ACP {

  //to read from gz
  def gis(s: String) = new GZIPInputStream(new BufferedInputStream(new FileInputStream(s)))

  //print N lines from a data set
  def printNlines[T](data: Array[Array[T]], start: Int, end: Int): Unit ={
    for (i <- start to end - 1) {
      println(data.toList(i).toList)
    }
  }

  def main(args: Array[String]): Unit = {
    //val path = "/home/user14/Documents/MachineLearning/DataTP/KDDCup1999Data/"
    val path = "/home/nico/Documents/bigData/machineLearning/MachineLearning/DataTP/KDDCup1999Data/"
    val file_name1 = "kddcup.data_1_percent_corrected"
    val file_name2 = "kddcup.names"
    val data_source = scala.io.Source.fromFile(path + file_name1).getLines.filter(!_.isEmpty()).map(_.split(",")).toArray
    //val data_source = Source.fromInputStream(gis(path + file_name1)).getLines.filter(!_.isEmpty()).map(_.split(",")).toArray
    val label_source = scala.io.Source.fromFile(path + file_name2).getLines.drop(1).map(_.split(":")(0)).toArray :+ "label"
    //printNlines(data_source, 0, 20)
    //println("column number:"+label_source.size)
    //println(label_source.toList)
    // duration, protocol_type, service, flag, src_bytes, dst_bytes, land, wrong_fragment, urgent, hot, num_failed_logins, logged_in, num_compromised, root_shell, su_attempted, num_root, num_file_creations, num_shells, num_access_files, num_outbound_cmds, is_host_login, is_guest_login, count, srv_count, serror_rate, srv_serror_rate, rerror_rate, srv_rerror_rate, same_srv_rate, diff_srv_rate, srv_diff_host_rate, dst_host_count, dst_host_srv_count, dst_host_same_srv_rate, dst_host_diff_srv_rate, dst_host_same_src_port_rate, dst_host_srv_diff_host_rate, dst_host_serror_rate, dst_host_srv_serror_rate, dst_host_rerror_rate, dst_host_srv_rerror_rate, label
    // 0, tcp, http, SF, 233, 504, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 0.00, 0.00, 0.00, 0.00, 1.00, 0.00, 0.00, 64, 199, 1.00, 0.00, 0.02, 0.03, 0.00, 0.00, 0.00, 0.00, normal.

    //var data_source = ordi.map { x => Array(x(0).toDouble, x(1).toDouble) }.toArray
    //val lineNb = data_source.size
    //val absc = (1 to lineNb).toArray.toIterator
    //val var_nb = 0
    //val x = data_source.map { x => Array(x(var_nb).toDouble, absc.next().toDouble) }.toArray
    //val x = data_source.map { x => x(var_nb).toDouble }.toArray
    //val y = data_source.map { x => x(1).toDouble }.toArray
    //println(lineNb+" "+x.toList)
    //val plot1 = smile.plot.plot(x, '.')

    // 1 - stocker les valeurs des variables non numériques possibles -> toSet
    val protocol_type_vals = data_source.map(x => x.toList(1)).toSet.toList.sorted
    val service_vals = data_source.map(x => x.toList(2)).toSet.toList.sorted
    val flag_vals = data_source.map(x => x.toList(3)).toSet.toList.sorted
    val label_vals = data_source.map(x => x.toList(41)).toSet.toList.sorted
    //label_vals.foreach(println)

    // 2 - remplacer ces valeurs par des étiquettes numériques
    val intIterator = Iterator.from(1)
    val protocol_type_valsInt = protocol_type_vals.map(x => (x, intIterator.next().toDouble)).toMap
    val intIterator2 = Iterator.from(1)
    val service_valsInt = service_vals.map(x => (x,intIterator2.next().toDouble)).toMap
    val intIterator3 = Iterator.from(1)
    val flag_valsInt = flag_vals.map(x => (x,intIterator3.next().toDouble)).toMap
    val intIterator4 = Iterator.from(1)
    val label_valsInt = label_vals.map(x => (x,intIterator4.next().toDouble)).toMap
    //label_valsInt.foreach(x => println(x))
    //println(label_valsInt("teardrop."))

    // 3 - dans les data remplacer toutes les string par leurs étiquettes numériques
    //for (x <- 4 to 40 by 1){print("x("+x+"), ")}
    val data_num = data_source.map(x => Array(x(0).toDouble,protocol_type_valsInt(x(1)), service_valsInt(x(2)), flag_valsInt(x(3)), x(4).toDouble, x(5).toDouble, x(6).toDouble, x(7).toDouble, x(8).toDouble, x(9).toDouble, x(10).toDouble, x(11).toDouble, x(12).toDouble, x(13).toDouble, x(14).toDouble, x(15).toDouble, x(16).toDouble, x(17).toDouble, x(18).toDouble, x(19).toDouble, x(20).toDouble, x(21).toDouble, x(22).toDouble, x(23).toDouble, x(24).toDouble, x(25).toDouble, x(26).toDouble, x(27).toDouble, x(28).toDouble, x(29).toDouble, x(30).toDouble, x(31).toDouble, x(32).toDouble, x(33).toDouble, x(34).toDouble, x(35).toDouble, x(36).toDouble, x(37).toDouble, x(38).toDouble, x(39).toDouble, x(40).toDouble, label_valsInt(x(41)).toDouble ))
    //printNlines(data_source,0, 5)
    //println()
    //printNlines(data_num,0, 5)

    // 4- on peut alors faire le boxplot également pour ces variables.
    val boxPlot_num = smile.plot.boxplot(data_num(10))
    //val boxPlot_num = smile.plot.boxplot(data_num, label_source)



    //val data0 = data_source.map { x => x.map { x => if(x.forall(_.isDigit)){x.toDouble} }.filter { x => x.getClass() == Double}}
    //val data = data0.map { x => x.map { x => x.toDouble } }
    //data_source.foreach { x => println(x.toList) }
    //    val plot1 = smile.plot.boxplot(data, label_source)
    //    plot1.setVisible(true)
    //    plot1.setSize(new Dimension(1000, 1000))
    //plot1.
    //    plot1.canvas.setTitle(label_source(var_nb)+"(line_nb)")
    //    plot1.canvas.setAxisLabels ("line_nb", label_source(var_nb))
    println("\n***boop***")
  }

}