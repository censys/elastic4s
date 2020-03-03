package com.sksamuel.elastic4s.mappings

import com.sksamuel.exts.OptionImplicits._

case class NestedFieldDefinition(name: String,
                                 analysis: Analysis = Analysis(),
                                 boost: Option[Double] = None,
                                 copyTo: Seq[String] = Nil,
                                 docValues: Option[Boolean] = None,
                                 dynamic: Option[String] = None,
                                 enabled: Option[Boolean] = None,
                                 includeInAll: Option[Boolean] = None,
                                 includeInParent: Option[Boolean] = None,
                                 includeInRoot: Option[Boolean] = None,
                                 index: Option[String] = None,
                                 indexOptions: Option[String] = None,
                                 fields: Seq[FieldDefinition] = Nil,
                                 norms: Option[Boolean] = None,
                                 nulls: Nulls = Nulls(),
                                 store: Option[Boolean] = None,
                                 termVector: Option[String] = None)
    extends FieldDefinition {

  type T = NestedFieldDefinition
  override def `type` = "nested"

  override def boost(boost: Double): T          = copy(boost = boost.some)
  override def docValues(docValues: Boolean): T = copy(docValues = docValues.some)

  override def analyzer(analyzer: String): T       = copy(analysis = analysis.copy(analyzer = analyzer.some))
  override def normalizer(normalizer: String): T   = copy(analysis = analysis.copy(normalizer = normalizer.some))
  override def searchAnalyzer(analyzer: String): T = copy(analysis = analysis.copy(searchAnalyzer = analyzer.some))

  override def nullable(nullable: Boolean): T = copy(nulls = nulls.copy(nullable = nullable.some))
  override def nullValue(nullvalue: Any): T   = copy(nulls = nulls.copy(nullValue = nullvalue.some))

  def dynamic(dynamic: String): T  = copy(dynamic = dynamic.some)
  def dynamic(dynamic: Boolean): T = copy(dynamic = dynamic.toString.some)

  override def fields(fields: Iterable[FieldDefinition]): T = copy(fields = fields.toSeq)

  override def copyTo(first: String, rest: String*): T = copyTo(first +: rest)
  override def copyTo(copyTo: Iterable[String]): T     = copy(copyTo = copyTo.toSeq)

  override def enabled(enabled: Boolean): T = copy(enabled = enabled.some)

  override def includeInAll(includeInAll: Boolean): T = copy(includeInAll = includeInAll.some)

  @deprecated(
    "This setting was removed from the Elasticsearch documentation in version 2.0. See the following discussion regarding removing support in a future version of elasticsearch: https://github.com/elastic/elasticsearch/issues/12461"
  )
  def includeInParent(includeInParent: Boolean): T = copy(includeInParent = includeInParent.some)

  @deprecated(
    "This setting was removed from the Elasticsearch documentation in version 2.0. See the following discussion regarding removing support in a future version of elasticsearch: https://github.com/elastic/elasticsearch/issues/12461"
  )
  def includeInRoot(includeInRoot: Boolean): T = copy(includeInRoot = includeInRoot.some)

  override def index(index: Boolean): T = copy(index = index.toString.some)

  override def norms(norms: Boolean): T = copy(norms = norms.some)

  override def store(b: Boolean): T = copy(store = b.some)

  override def termVector(t: String): T = copy(termVector = t.some)
}
