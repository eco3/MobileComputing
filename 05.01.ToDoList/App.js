import React, { Component } from 'react';
import {
  Button,
  SafeAreaView,
  StyleSheet,
  TextInput,
  View,
  Text,
  FlatList
} from 'react-native';

const Separator = () => (
  <View style={styles.separator} />
);

const Item = ({ title }) => (
  <View style={styles.item}>
    <Text style={styles.title}>{title}</Text>
  </View>
);

export default class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      todostodoInput: null,
      todos: []
    };
  }

  render() {
      const renderItem = ({ item }) => (
        <Item title={item.title} />
      );

      return (
        <SafeAreaView style={styles.container}>
          <View>
            <TextInput
              style={styles.input}
              placeholder="Enter a todo..."
              onChangeText={todoInput => this.setState({todoInput})}
              ref={input => { this.textInput = input }}
              />
            <Button
              title="Add todo"
              onPress={() => {
                const { todoInput } = this.state;
                const { todos } = this.state;

                todos.push({
                  'id': todos.length.toString(),
                  'title': todoInput
                });

                this.setState({todos})
                this.textInput.clear()
              }}
            />
          </View>
          <Separator />
          <FlatList
            data={this.state.todos}
            extraData={this.state.todos}
            renderItem={renderItem}
            keyExtractor={item => item.id}
          />
        </SafeAreaView>
      );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginHorizontal: 16,
  },
  input: {
    height: 40,
    margin: 12,
    borderWidth: 1,
    padding: 10,
  },
  title: {
    color: '#000',
    textAlign: 'left',
    marginVertical: 12,
  },
  separator: {
    marginVertical: 8,
    borderBottomColor: '#737373',
    borderBottomWidth: StyleSheet.hairlineWidth,
  },
  item: {
    backgroundColor: '#ddd',
    padding: 5,
    marginVertical: 4,
    marginHorizontal: 16,
  },
});
