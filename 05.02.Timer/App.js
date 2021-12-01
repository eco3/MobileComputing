import React, { Component } from 'react';
import {
  SafeAreaView,
  StyleSheet,
  View,
  Text,
} from 'react-native';

export default class App extends Component {
  constructor(props) {
    super(props)
    this.state = {};
  }

  render() {
      return (
        <SafeAreaView style={styles.container}>
          <View>
            <Text style={styles.title}>Hello World</Text>
          </View>
        </SafeAreaView>
      );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginHorizontal: 16,
  },
  title: {
    color: '#000',
    textAlign: 'left',
    marginVertical: 12,
  },
});
