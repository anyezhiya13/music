<template>
    <div class="my-music">
      <div class="album-slide">
          <img class="album-img " :src="attachImageUrl(avatar)"/>
          <ul class="album-info">
            <li>昵称：{{username}}</li>
            <li>性别：{{userSex}}</li>
            <li>生日：{{birth}}</li>
            <li>故乡：{{location}}</li>
          </ul>
      </div>
      <div class="album-content">
        <div class="album-title">
          个性签名：{{introduction}}
        </div>
        <div class="songs-body">
          <album-content :songList="collectList">
          <template slot="title">我的收藏</template>
          </album-content>
        </div>
      </div>
    </div>
</template>

<script>
import { mixin } from '../mixins'
import { mapGetters } from 'vuex'
import {getCollectOfUserId, getUserOfId, songOfSongId} from '../api/index'
import AlbumContent from '../components/AlbumContent'

export default {
  name: 'my-music',
  mixins: [mixin],
  components: {
    AlbumContent
  },
  data () {
    return {
      avatar: '', // 用户的头像
      username: '', // 用户的用户名
      userSex: '', // 用户的性别
      birth: '', // 生日
      location: '', // 地区
      introduction: '', // 签名
      collection: [], // 收藏的歌曲列表
      collectList: [] // 收藏的歌曲列表(带歌曲详情)
    }
  },
  computed: {
    ...mapGetters([
      'listOfSongs',
      'userId' // 当前登录用户id
    ])
  },
  mounted () {
    this.getMsg(this.userId)
    this.getCollection(this.userId)
  },
  methods: {
    getMsg (userId) {
      getUserOfId(userId)
        .then(res => {
          this.avatar = res.avatar
          this.username = res.username
          if (res.sex === 0) {
            this.userSex = '女'
          } else if (res.sex === 1) {
            this.userSex = '男'
          }
          this.birth = this.getBirth(res.birth)
          this.location = res.location
          this.introduction = res.introduction
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 获取我的收藏列表
    getCollection (userId) {
      getCollectOfUserId(userId).then(res => {
        this.collection = res
        // 通过歌曲id获取歌曲信息
        for (let item of this.collection) {
          this.getSongsOfIds(item.songId)
        }
      })
        .catch(err => {
          console.log(err)
        })
    },
    // 通过歌曲id获取歌曲信息
    getSongsOfIds (id) {
      songOfSongId(id)
        .then(res => {
          this.collectList.push(res)
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 解析日期
    getBirth (cellValue) {
      if (cellValue == null || cellValue === '') return ''
      const date = new Date(cellValue)
      const year = date.getFullYear()
      const month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
      const day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
      return year + '-' + month + '-' + day
    }
  }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/my-music.scss'
</style>
